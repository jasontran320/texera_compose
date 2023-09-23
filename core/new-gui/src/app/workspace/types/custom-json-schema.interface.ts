import { JSONSchema7, JSONSchema7Definition } from "json-schema";

export const hideTypes = ["regex", "equals"] as const;
export type HideType = (typeof hideTypes)[number];

export type AttributeTypeEnumRule = ReadonlyArray<string>;
export type AttributeTypeConstRule = Readonly<{
  $data?: string;
}>;
export type AttributeTypeAllOfRule = ReadonlyArray<{
  if: {
    [key: string]: {
      valEnum?: string[];
    };
  };
  then: {
    enum?: AttributeTypeEnumRule;
  };
}>;
export type AttributeTypeRuleSet = Readonly<{
  enum?: AttributeTypeEnumRule;
  const?: AttributeTypeConstRule;
  allOf?: AttributeTypeAllOfRule;
}>;

export type AttributeTypeRuleSchema = Readonly<{
  [key: string]: AttributeTypeRuleSet;
}>;

export interface CustomJSONSchema7 extends JSONSchema7 {
  propertyOrder?: number;
  properties?: {
    [key: string]: CustomJSONSchema7 | boolean;
  };
  items?: CustomJSONSchema7 | boolean | JSONSchema7Definition[];

  // new custom properties:
  autofill?: "attributeName" | "attributeNameList";
  autofillAttributeOnPort?: number;
  attributeTypeRules?: AttributeTypeRuleSchema;

  "enable-presets"?: boolean; // include property in schema of preset

  dependOn?: string;
  toggleHidden?: string[]; // the field names which will be toggle hidden or not by this field.

  hideExpectedValue?: string;
  hideTarget?: string;
  hideType?: HideType;
  hideOnNull?: boolean;

  additionalEnumValue?: string;
}
