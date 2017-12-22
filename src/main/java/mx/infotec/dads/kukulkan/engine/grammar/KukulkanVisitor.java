package mx.infotec.dads.kukulkan.engine.grammar;

import static mx.infotec.dads.kukulkan.engine.grammar.GrammarPropertyMapping.getDateType;
import static mx.infotec.dads.kukulkan.engine.grammar.GrammarPropertyMapping.getNumericType;
import static mx.infotec.dads.kukulkan.engine.grammar.GrammarUtil.addContentType;
import static mx.infotec.dads.kukulkan.engine.grammar.GrammarUtil.addMetaData;
import static mx.infotec.dads.kukulkan.engine.grammar.GrammarUtil.createJavaProperty;

import java.util.ArrayList;
import java.util.Optional;

import mx.infotec.dads.kukulkan.engine.domain.core.Constraint;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaProperty;
import mx.infotec.dads.kukulkan.grammar.kukulkanBaseVisitor;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.BlobFieldTypeContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.BooleanFieldTypeContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.DateFieldTypeContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.EntityContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.EntityFieldContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.NumericFieldTypeContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.StringFieldTypeContext;
import mx.infotec.dads.kukulkan.util.DataBaseMapping;

/**
 * KukulkanGrammarVisitor implements the
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class KukulkanVisitor extends kukulkanBaseVisitor<VisitorContext> {

    private final VisitorContext vctx = new VisitorContext(new ArrayList<DomainModelElement>());
    private DomainModelElement dme = null;
    private EntityFieldContext efc = null;
    private String propertyName = null;
    private JavaProperty javaProperty = null;
    private Constraint constraint = null;

    @Override
    public VisitorContext visitEntity(EntityContext ctx) {
        dme = DomainModelElement.createDomainModelElement();
        addMetaData(ctx, dme);
        getVctx().getElements().add(dme);
        return super.visitEntity(ctx);
    }

    @Override
    public VisitorContext visitEntityField(EntityFieldContext ctx) {
        efc = ctx;
        propertyName = ctx.id.getText();
        constraint = new Constraint();
        super.visitEntityField(ctx);
        javaProperty.setConstraint(constraint);
        return vctx;
    }

    @Override
    public VisitorContext visitStringFieldType(StringFieldTypeContext ctx) {
        Optional<GrammarPropertyType> optional = Optional.of(GrammarPropertyMapping.getMap().get(ctx.name.getText()));
        processFieldType(optional);
        return super.visitStringFieldType(ctx);
    }

    @Override
    public VisitorContext visitDateFieldType(DateFieldTypeContext ctx) {
        Optional<GrammarPropertyType> optional = Optional
                .of(GrammarPropertyMapping.getMap().get(getDateType(ctx.dateTypes())));
        processFieldType(optional);
        return super.visitDateFieldType(ctx);
    }

    @Override
    public VisitorContext visitNumericFieldType(NumericFieldTypeContext ctx) {
        Optional<GrammarPropertyType> optional = Optional
                .of(GrammarPropertyMapping.getMap().get(getNumericType(ctx.numericTypes())));
        processFieldType(optional);
        return super.visitNumericFieldType(ctx);
    }

    @Override
    public VisitorContext visitBlobFieldType(BlobFieldTypeContext ctx) {
        Optional<GrammarPropertyType> optional = Optional.of(GrammarPropertyMapping.getMap().get(ctx.name.getText()));
        processFieldType(optional);
        return super.visitBlobFieldType(ctx);
    }

    @Override
    public VisitorContext visitBooleanFieldType(BooleanFieldTypeContext ctx) {
        Optional<GrammarPropertyType> optional = Optional.of(GrammarPropertyMapping.getMap().get(ctx.name.getText()));
        processFieldType(optional);
        return super.visitBooleanFieldType(ctx);
    }

    /**
     * Visit Constraints
     */
    @Override
    public VisitorContext visitRequiredValidator(kukulkanParser.RequiredValidatorContext ctx) {
        constraint.setNullable(false);
        dme.setHasNotNullElements(true);
        dme.setHasConstraints(true);
        javaProperty.setHasConstraints(true);
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitMinLengthValidator(kukulkanParser.MinLengthValidatorContext ctx) {
        constraint.setMinLength(ctx.NUMERIC_VALUE().getText());
        dme.setHasConstraints(true);
        javaProperty.setSizeValidation(true);
        javaProperty.setHasConstraints(true);
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitMaxLengthValidator(kukulkanParser.MaxLengthValidatorContext ctx) {
        constraint.setMaxLength(ctx.NUMERIC_VALUE().getText());
        dme.setHasConstraints(true);
        javaProperty.setSizeValidation(true);
        javaProperty.setHasConstraints(true);
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitPatternValidator(kukulkanParser.PatternValidatorContext ctx) {
        constraint.setPattern(ctx.PATTERN_VALUE().getText().substring(1, ctx.PATTERN_VALUE().getText().length()-1));
        dme.setHasConstraints(true);
        javaProperty.setHasConstraints(true);
        javaProperty.setHasConstraints(true);
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitMinValidator(kukulkanParser.MinValidatorContext ctx) {
        constraint.setMinNumber(ctx.NUMERIC_VALUE().getText());
        dme.setHasConstraints(true);
        javaProperty.setHasConstraints(true);
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitMaxValidator(kukulkanParser.MaxValidatorContext ctx) {
        constraint.setMaxNumber(ctx.NUMERIC_VALUE().getText());
        dme.setHasConstraints(true);
        javaProperty.setHasConstraints(true);
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitMinBytesValidator(kukulkanParser.MinBytesValidatorContext ctx) {
        constraint.setMinByte(ctx.NUMERIC_VALUE().getText());
        dme.setHasConstraints(true);
        javaProperty.setHasConstraints(true);
        javaProperty.setSizeValidation(true);
        return super.visitChildren(ctx);
    }

    @Override
    public VisitorContext visitMaxBytesValidator(kukulkanParser.MaxBytesValidatorContext ctx) {
        constraint.setMaxByte(ctx.NUMERIC_VALUE().getText());
        dme.setHasConstraints(true);
        javaProperty.setHasConstraints(true);
        javaProperty.setSizeValidation(true);
        return super.visitChildren(ctx);
    }

    public VisitorContext getVctx() {
        return vctx;
    }

    public void processFieldType(Optional<GrammarPropertyType> optional) {
        if (optional.isPresent()) {
            GrammarPropertyType grammarPropertyType = optional.get();
            javaProperty = createJavaProperty(efc, propertyName, grammarPropertyType);
            dme.addProperty(javaProperty);
            addContentType(dme, propertyName, grammarPropertyType);
            GrammarMapping.addImports(dme.getImports(), javaProperty);
            DataBaseMapping.fillModelMetaData(dme, javaProperty);
        }
    }
}
