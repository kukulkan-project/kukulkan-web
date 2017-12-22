(function() {
    'use strict';

    angular
        .module('${projectName}App')
        .controller('${entity}DialogController', ${entity}DialogController);

    ${entity}DialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', '${entity}'];

    function ${entity}DialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, ${entity}) {
        var vm = this;

        vm.${entityCamelCase} = entity;
        vm.clear = clear;
        <#if hasTimeProperties == true>
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        </#if>
        <#if hasBlobProperties == true>
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        </#if>
        vm.save = save;
          
        <#if hasTimeProperties == true>
        	<#list properties as property>
        	<#if property.time == true> 
        vm.datePickerOpenStatus.${property.name} = false;
            </#if>
        	</#list>
        </#if>

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.${entityCamelCase}.id !== null) {
                ${entity}.update(vm.${entityCamelCase}, onSaveSuccess, onSaveError);
            } else {
                ${entity}.save(vm.${entityCamelCase}, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('${projectName}App:${entityCamelCase}Update', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
        
        <#if hasBlobProperties == true>
        	<#list properties as property>
        	<#if property.blob == true> 
        vm.set${property.name?cap_first} = function ($file, ${entityCamelCase}) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        ${entityCamelCase}.${property.name} = base64Data;
                        ${entityCamelCase}.${property.name}ContentType = $file.type;
                    });
                });
            }
        };
            </#if>
        	</#list>
        </#if>

        <#if hasTimeProperties == true>
        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
        </#if>
    }
})();
