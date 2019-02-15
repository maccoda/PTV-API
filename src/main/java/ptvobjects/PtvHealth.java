package ptvobjects;


/**
 * Health object as defined by PTV. This object is used to check the connection with the API as well
 * as check time and credentials provided.
 *
 * @author D. Maccora
 */
public class PtvHealth implements PtvBasicObject {

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PtvHealth{");
        sb.append("securityTokenOK=").append(securityTokenOK);
        sb.append(", clientClockOK=").append(clientClockOK);
        sb.append(", memcacheOK=").append(memcacheOK);
        sb.append(", databaseOK=").append(databaseOK);
        sb.append('}');
        return sb.toString();
    }
}
