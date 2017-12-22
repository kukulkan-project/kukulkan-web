			<div class="form-group">
				<label class="control-label" data-translate="${projectName}App.${entityCamelCase}.${property.name}"
					for="field_${property.name}">${property.name}</label>
				<input type="text" class="form-control" name="${property.name}" id="field_${property.name}"
					ng-model="vm.${entityCamelCase}.${property.name}" 
<#if property.constraint.nullable==false>
					required</#if> <#if property.constraint.minLength??>ng-minlength="${property.constraint.minLength}"</#if> <#if property.constraint.maxLength??>ng-maxlength="${property.constraint.maxLength}"</#if> <#if property.constraint.pattern??>ng-pattern="/${property.constraint.pattern}/"</#if>
					/>
			<#if property.hasConstraints==true>				
				<div ng-show="editForm.${property.name}.$invalid">
				<#if property.constraint.nullable==false>
	                <p class="help-block"
	                    ng-show="editForm.${property.name}.$error.required" data-translate="entity.validation.required">
	                    This field is required.
	                </p>
	            </#if>
				<#if property.constraint.minLength??>
	                <p class="help-block"
	                    ng-show="editForm.${property.name}.$error.minlength" data-translate="entity.validation.minlength" translate-value-min="${property.constraint.minLength}">
	                    This field is required to be at least ${property.constraint.minLength} characters.
	                </p>
				</#if>
				<#if property.constraint.maxLength??>
	                <p class="help-block"
	                    ng-show="editForm.${property.name}.$error.maxlength" data-translate="entity.validation.maxlength" translate-value-max="${property.constraint.maxLength}">
	                    This field cannot be longer than ${property.constraint.maxLength} characters.
	                </p>
				</#if>
				<#if property.constraint.pattern??>
	                <p class="help-block"
	                    ng-show="editForm.${property.name}.$error.pattern" data-translate="entity.validation.pattern" translate-value-pattern="${property.constraint.pattern}">
	                    This field should follow pattern for "${property.constraint.pattern}".
	                </p>
            	</#if>
            	</div>	
            </#if>
			</div>
