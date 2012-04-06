package com.google.code.ts3query.model.manager;

import java.util.Map;

import com.google.code.ts3query.TeamspeakResponse;
import com.google.code.ts3query.model.Entity;

public class Clients extends Entity {

  /**
   * Deletes a clients properties from the database.
   * 
   * @param clientDBID
   */
  public void clientdbdelete(int clientDBID) {
    send(command("clientdbdelete").with("cldbid", clientDBID));
  }

  /**
   * Changes a clients settings using given properties.
   * 
   * @param clientDBID
   * @param parameters
   */
  public void clientdbedit(int clientDBID, Map<String, ? extends Object> parameters) {
    send(command("clientdbedit").with("cldbid", clientDBID).with(parameters));
  }

  /**
   * Displays a list of client identities known by the server including their
   * database ID, last nickname, etc.
   * 
   * @return
   * 
   *         <code>cldbid=7 client_unique_identifier=DZhdQU58qyooEK4Fr8Ly738hEmc= client_nickname=MuhChy client_created=1259147468 client_lastconnected=1259421233</code>
   */
  public TeamspeakResponse clientdblist() { // ClientIdentity
    return send(command("clientdblist"));
  }

  /**
   * Removes a set of specified permissions from a client. Multiple permissions
   * can be removed at once.
   * 
   * @param clientDBID
   * @param permID
   */
  public void clientdelperm(int clientDBID, int... permID) {
    send(command("clientdelperm").with("cldbid", clientDBID).with("permid", permID));
  }

  /**
   * Changes a clients settings using given properties. For detailed
   * information, see Client Properties.
   * 
   * @param clientID
   * @param parameters
   */
  public void clientedit(int clientID, Map<String, ? extends Object> parameters) {
    send(command("clientedit").with("clid", clientID).with(parameters));
  }

  /**
   * Displays a list of clients matching a given name pattern.
   * 
   * @param clientName
   * @return <code>clid=7 client_nickname=Sven</code>
   */
  public TeamspeakResponse clientfind(String clientName) {
    return send(command("clientfind").with("pattern", clientName));
  }

  /**
   * Displays the database ID matching the unique identifier specified by cluid.
   * 
   * @param clientUID
   * @return <code>cluid=dyjxkshZP6bz0n3bnwFQ1CkwZOM= cldbid=32</code>
   */
  public TeamspeakResponse clientgetdbidfromuid(String clientUID) {
    return send(command("clientgetdbidfromuid").with("cluid", clientUID));
  }

  /**
   * Displays all client IDs matching the unique identifier specified by cluid.
   * 
   * @param clientUID
   * @return <code>cluid=dyjxkshZP6bz0n3bnwFQ1CkwZOM= clid=1 name=Janko</code>
   */
  public TeamspeakResponse clientgetids(String clientUID) {
    return send(command("clientgetids").with("cluid", clientUID));
  }

  /**
   * Displays the unique identifier and nickname matching the database ID
   * specified by cldbid.
   * 
   * @param clientDBID
   * @return <code>cluid=dyjxkshZP6bz0n3bnwFQ1CkwZOM= cldbid=32 name=Janko</code>
   */
  public TeamspeakResponse clientgetnamefromdbid(int clientDBID) {
    return send(command("clientgetnamefromdbid").with("cldbid", clientDBID));
  }

  /**
   * Displays the database ID and nickname matching the unique identifier
   * specified by cluid.
   * 
   * @param clientUID
   * @return <code>cluid=dyjxkshZP6bz0n3bnwFQ1CkwZOM= cldbid=32 name=Janko</code>
   */
  public TeamspeakResponse clientgetnamefromuid(String clientUID) {
    return send(command("clientgetnamefromuid").with("cluid", clientUID));
  }

  /**
   * Displays detailed configuration information about a client including unique
   * ID, nickname, client version, etc.
   * 
   * @param clientId
   * @return
   * 
   *         <code>client_unique_identifier=P5H2hrN6+gpQI4n\/dXp3p17vtY0= client_nickname=Rabe85 client_version=3.0.0-alpha24\s[Build:\s8785]\s(UI:\s8785) ...</code>
   */
  public TeamspeakResponse clientinfo(int clientId) {
    return send(command("clientinfo").with("clid", clientId));
  }

  /**
   * Kicks one or more clients specified with clid from their currently joined
   * channel or from the server, depending on reasonid. The reasonmsg parameter
   * specifies a text message sent to the kicked clients. This parameter is
   * optional and may only have a maximum of 40 characters.
   * 
   * @param reasonid
   *          4=channel or 5=server
   * @param reasonmsg
   *          an optional message, use <code>null</code> to skip
   * @param clientID
   *          any number of client IDs to kick
   */
  public void clientkick(int reasonid, String reasonmsg, int... clientID) {
    // FIXME doesn't work right yet
    send(command("clientkick")
        .with("reasonid", reasonid)
        .with("reasonmsg", reasonmsg)
        .with("clid", clientID));
  }

  /**
   * Moves one or more clients specified with clid to the channel with ID cid.
   * If the target channel has a password, it needs to be specified with cpw. If
   * the channel has no password, the parameter can be omitted.
   * 
   * @param channelID
   * @param channelPassword
   *          optional channel password, <code>null</code> to skip
   * @param clientID
   */
  public void clientmove(int channelID, String channelPassword, int... clientID) {
    send(command("clientmove")
        .with("cid", channelID)
        .with("cpw", channelPassword)
        .with("clid", clientID));
  }

  /**
   * Displays a list of permissions defined for a client.
   * 
   * @param clientDBID
   * @return
   * 
   *         <code>cldbid=2 permid=4353 permvalue=1 permnegated=0 permskip=0|permid=17276 permvalue=50 permnegated=0 permskip=0|permid=21415 ...</code>
   */
  public TeamspeakResponse clientpermlist(int clientDBID) {
    return send(command("clientpermlist").with("cldbid", clientDBID));
  }

  /**
   * Sends a poke message to the client specified with clid.
   * 
   * @param msg
   * @param clid
   */
  public void clientpoke(String msg, int... clid) {
    send(command("clientpoke").with("msg", msg).with("clid", clid));
  }

  /**
   * Updates your own ServerQuery login credentials using a specified username.
   * The password will be auto-generated.
   * 
   * @param username
   * @return the auto-generated password
   */
  public String clientsetserverquerylogin(String username) {
    return send(command("clientsetserverquerylogin").with("client_login_name", username))
        .getFirstResponse().get("client_login_password");
  }

  /**
   * Change your ServerQuery clients settings using given properties.
   * 
   * @param parameters
   */
  public void clientupdate(Map<String, ? extends Object> parameters) {
    send(command("clientupdate").with(parameters));
  }

}
