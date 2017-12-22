/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mx.infotec.dads.kukulkan.util;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.infotec.dads.kukulkan.engine.domain.core.GeneratedElement;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;

/**
 * The FileUtil Class is used for common File operations
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
    }

    /**
     * Closes this stream and releases any system resources associated with it.
     * If the stream is already closed then invoking this method has no effect.
     *
     * <p>
     * As noted in {@link AutoCloseable#close()}, cases where the close may fail
     * require careful attention. It is strongly advised to relinquish the
     * underlying resources and to internally <em>mark</em> the
     * {@code Closeable} as closed, prior to throwing the {@code IOException}.
     *
     * @throws IOException
     *             if an I/O error occurs
     */
    public static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException ioException) {
                LOGGER.error("The resource actually exist: ", ioException);
            }
        }
    }

    public static Path buildPath(String proyectoId, BasePathEnum path, String filePath, String outPutDir) {
        return Paths.get(outPutDir + proyectoId + "/" + path.getPath() + "/" + filePath);
    }

    public static boolean createParentsFileIfNotExist(Path path) {
        if (!path.toFile().exists()) {
            return createDirectories(path.getParent());
        } else {
            return false;
        }
    }

    public static boolean createFileIfNotExist(File file) {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            LOGGER.error("FileUtil.createFileIfNotExist: ", e);
            return false;
        }
    }

    public static boolean createDirectories(Path path) {
        try {
            Files.createDirectories(path.getParent());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean saveToFile(GeneratedElement ge) {
        createDirectories(ge.getPath());
        LOGGER.info("saveFile" + ge.getPath().toString());
        try (final BufferedWriter out = Files.newBufferedWriter(ge.getPath(), StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            out.write(ge.getContent());
            return true;
        } catch (IOException ioe) {
            LOGGER.error("FileUtil.createFileIfNotExist: ", ioe);
            return false;
        }
    }

    public static boolean saveToFile(GeneratorContext genCtx) {
        saveDataModelElements(genCtx);
        saveReadmeToFile(genCtx);
        return true;
    }

    public static void saveReadmeToFile(GeneratorContext genCtx) {
        genCtx.getDomainModel().getGeneratedElements().forEach(FileUtil::saveToFile);
    }

    public static void saveDataModelElements(GeneratorContext genCtx) {
        genCtx.getDomainModel().getDomainModelGroup().forEach(dmg -> dmg.getDomainModelElements()
                .forEach(dme -> dme.getGeneratedElements().forEach(FileUtil::saveToFile)));
    }

    public static void createZip(Path path, String compressedName) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(path.getParent().toFile(), compressedName + ".zip"));
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = path.toFile();
        zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }

        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        } catch (IOException e) {
            LOGGER.error("FileUtil.zipFile: ", e);
        }

    }

    public static void replaceInFile(Path path, String targetText, String newText) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);
        content = content.replaceAll(targetText, newText);
        Files.write(path, content.getBytes(charset));
    }
}
