			<div class="form-group">
				<label class="control-label" data-translate="${projectName}App.${entityCamelCase}.${property.name}"
					for="field_${property.name}">${property.name}</label>
				<textarea class="form-control" name="${property.name}" id="field_${property.name}"
					ng-model="vm.${entityCamelCase}.${property.name}" <#if property.constraint.nullable==false>required</#if> <#if property.constraint.minByte??>minbytes="${property.constraint.minByte}"</#if> <#if property.constraint.maxByte??>maxbytes="${property.constraint.maxByte}" </#if>
					></textarea>
			<#if property.hasConstraints==true>	
				<div ng-show="editForm.${property.name}.$invalid">			
				<#if property.constraint.nullable==false>
	                <p class="help-block"
	                    ng-show="editForm.${property.name}.$error.required" data-translate="entity.validation.required">
	                    This field is required.
	                </p>
	            </#if>
				<#if property.constraint.minByte??>
	                <p class="help-block"
	                   ng-show="editForm.${property.name}.$error.minbytes" data-translate="entity.validation.minbytes" translate-value-min="${property.constraint.minByte}">
	                    This field should be at least ${property.constraint.minByte}.
	                </p>
				</#if>
				<#if property.constraint.maxByte??>
	                <p class="help-block"
	                   ng-show="editForm.${property.name}.$error.maxbytes" data-translate="entity.validation.maxbytes" translate-value-max="${property.constraint.maxByte}">
	                    This field cannot be more than ${property.constraint.maxByte}.
	                </p>
				</#if>
            	</div>	
            </#if>
			</div>
