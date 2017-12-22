{
    "${projectName}App": {
        "${entityCamelCase}" : {
            "home": {
                "title": "${entityCamelCasePlural}",
                "createLabel": "Crear nuevo ${entity}",
                "createOrEditLabel": "Crear o editar ${entity}",
                "search": "Buscar ${entity}"
            },
            "created": "Un nuevo ${entity} ha sido creado con el identificador {{ param }}",
            "updated": "Un ${entity} ha sido actualizado con el identificador {{ param }}",
            "deleted": "Un ${entity} ha sido eliminado con el identificador {{ param }}",
            "delete": {
                "question": "¿Seguro que quiere eliminar ${entity} {{ id }}?"
            },
            "detail": {
                "title": "${entity}"
            },
           <#list properties as property>
            "${property.name}": "${property.name?cap_first}"<#sep>, </#sep>
           </#list>
        }
    }
}
