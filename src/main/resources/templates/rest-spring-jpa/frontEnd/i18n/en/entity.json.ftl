{
    "${projectName}App": {
        "${entityCamelCase}" : {
            "home": {
                "title": "${entityCamelCasePlural}",
                "createLabel": "Create a new ${entity}",
                "createOrEditLabel": "Create or edit a ${entity}"
            },
            "created": "A new ${entity} is created with identifier {{ param }}",
            "updated": "A ${entity} is updated with identifier {{ param }}",
            "deleted": "A ${entity} is deleted with identifier {{ param }}",
            "delete": {
                "question": "Are you sure you want to delete ${entity} {{ id }}?"
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
