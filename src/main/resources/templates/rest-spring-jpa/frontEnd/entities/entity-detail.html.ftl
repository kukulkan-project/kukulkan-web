
<div>
    <h2><span data-translate="${projectName}App.${entityCamelCase}.detail.title">${entity}</span> {{vm.${entityCamelCase}.id}}</h2>
    <hr>
    <jhi-alert-error></jhi-alert-error>
    <dl class="dl-horizontal jh-entity-details">
        <dt><span data-translate="${projectName}App.${entityCamelCase}.${primaryKey.name}">${primaryKey.name}</span></dt>
        <dd>
			<span>{{vm.${entityCamelCase}.${primaryKey.name}}}</span>
		<dd>
		<#list properties as property>
        	<#if property.name?ends_with("ContentType") == false>
	        	<#if  property.blob == true>
	        		<#if property.clob == true>
	        		<#include "./read/textblob.ftl">
		    	    <#else>
		    	    <#include "./read/blob.ftl">
	        		</#if>
		    	<#elseif property.time == true>
		    		<#if  property.zoneDateTime == true>
		        		<#include "./read/zonedatetime.ftl">
			    	<#elseif property.localDate == true>
			    		<#include "./read/localdate.ftl">
		    		<#else>
		    		    <#include "./read/instant.ftl">
		    		</#if>
		        <#else> 
		        	<#include "./read/text.ftl">
		    	</#if>
        	</#if>
		</#list>
    </dl>

    <button type="submit"
            ui-sref="{{ vm.previousState }}"
            class="btn btn-info">
        <span class="glyphicon glyphicon-arrow-left"></span>&nbsp;<span data-translate="entity.action.back"> Back</span>
    </button>

    <button type="button" ui-sref="${entityCamelCase}-detail.edit({id:vm.${entityCamelCase}.id})" class="btn btn-primary">
        <span class="glyphicon glyphicon-pencil"></span>
        <span class="hidden-sm-down" data-translate="entity.action.edit"> Edit</span>
    </button>
</div>
