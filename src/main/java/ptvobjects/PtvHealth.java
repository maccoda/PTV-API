package ptvobjects;


/**
 * Health object as defined by PTV. This object is used to check the connection with the API as well
 * as check time and credentials provided.
 *
 * @author D. Maccora
 */
public class PtvHealth implements PtvObject {

    private boolean securityTokenOK, clientClockOK, memcacheOK, databaseOK;


    public boolean isSecurityToken() {
        return securityTokenOK;
    }

    public boolean isClientClockOK() {
        return clientClockOK;
    }

    public boolean isMemcache() {
        return memcacheOK;
    }

    public boolean isDatabase() {
        return databaseOK;
    }

    public boolean isAllGood() {
        return securityTokenOK && clientClockOK && memcacheOK && databaseOK;
    }


}
