package ptvobjects;


/**
 * Health object as defined by PTV. This object is used to check the connection with the API as well
 * as check time and credentials provided.
 *
 * @author D. Maccora
 */
public class PtvHealth implements PtvObject {

    private boolean securityToken, clientClock, memcache, database;


    public boolean isSecurityToken() {
        return securityToken;
    }

    public boolean isClientClock() {
        return clientClock;
    }

    public boolean isMemcache() {
        return memcache;
    }

    public boolean isDatabase() {
        return database;
    }

    public boolean isAllGood() {
        return securityToken && clientClock && memcache && database;
    }


}
