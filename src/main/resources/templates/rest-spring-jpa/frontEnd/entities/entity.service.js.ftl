(function() {
    'use strict';
    angular
        .module('${projectName}App')
        .factory('${entity}', ${entity});

    ${entity}.$inject = ['$resource'<#if hasTimeProperties == true>, 'DateUtils'</#if>];

    function ${entity} ($resource<#if hasTimeProperties == true>, DateUtils</#if>) {
        var resourceUrl = 'api/${entityCamelCasePlural}/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        <#if hasTimeProperties == true>
        			    	<#list properties as property>
				        		<#if property.time == true> 
				        			<#if  property.zoneDateTime == true>
				        data.${property.name} = DateUtils.convertDateTimeFromServer(data.${property.name});
							    	<#elseif property.localDate>
						data.${property.name} = DateUtils.convertLocalDateFromServer(data.${property.name});
							    	</#if>
				            	</#if>
				        	</#list>
        				</#if>
                    }
                    return data;
                }
            },
            <#if hasLocalDate == true>
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
				<#if hasTimeProperties == true>
        			<#list properties as property>
				    	<#if property.time == true> 
				        	<#if  property.localDate == true>
				    copy.${property.name} = DateUtils.convertLocalDateToServer(copy.${property.name});
							</#if>
				    	</#if>
					</#list>
        		</#if>
        		return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
				<#if hasTimeProperties == true>
        			<#list properties as property>
				    	<#if property.time == true> 
				        	<#if  property.localDate == true>
				    copy.${property.name} = DateUtils.convertLocalDateToServer(copy.${property.name});
							</#if>
				    	</#if>
					</#list>
        		</#if>
                    return angular.toJson(copy);
                }
            }
            <#else>
            'update': { method:'PUT' }
            </#if>
        });
    }
})();