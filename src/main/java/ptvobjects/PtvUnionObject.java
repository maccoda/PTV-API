package ptvobjects;

/**
 * An object that is obtained from PTV request which involves a union type. That is it cannot simply apply JSON to data
 * type transformation but will require a descriminator to determine which type to populate.
 */
public interface PtvUnionObject extends PtvObject {
}
