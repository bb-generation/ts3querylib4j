package com.google.code.ts3query.model.manager;

import com.google.code.ts3query.TeamspeakResponse;
import com.google.code.ts3query.model.Entity;

public class Permissions extends Entity {

  /**
   * Restores the default permission settings on the selected virtual server and
   * creates a new initial administrator token. Please note that in case of an
   * error during the permreset call - e.g. when the database has been modified
   * or corrupted - the virtual server will be deleted from the database.
   * 
   * @return new initial administrator token
   */
  public String reset() {
    return send(command("permreset")).getFirstResponse().get("token");
  }

  /**
   * Displays the IDs of all clients, channels or groups using the permission
   * specified with permid.
   * 
   * @param permID
   * @return <code>t=0 id1=1 id2=0 p=4353|t=0 id1=2 id2=0 p=4353</code>
   */
  public TeamspeakResponse find(int permID) {
    // TODO turn into object
    return send(command("permfind").with("permid", permID));
  }

  /**
   * Displays a list of permissions available on the server instance including
   * ID, name and description.
   * 
   * @return
   * 
   *         <code>permid=21413 permname=b_client_channel_textmessage_send permdesc=Send\stext\smessages\sto\schannel|permid=21414 permname=i_client_talk_power ...</code>
   */
  public TeamspeakResponse getList() {
    // TODO turn into object, make iteratable
    return send(command("permissionlist"));
  }

  /**
   * Displays all permissions assigned to a client for the channel specified
   * with cid. If permid is set to 0, all permissions will be displayed.
   * 
   * @param channelID
   * @param clientDBID
   * @param permID
   * @return <code>t=0 id1=5 id2=0 p=37 v=1 n=0 s=0|t=0 id1=5 id2=0 p=38 v=1 n=0 s=0 ...</code>
   */
  public TeamspeakResponse getOverview(int channelID, int clientDBID, int permID) {
    // TODO turn into object
    return send(command("permoverview").with("cid", channelID).with("cldbid", clientDBID)
        .with("permid", permID));
  }

  /**
   * Displays the database ID of one or more permissions specified by permsid.
   * 
   * @param permissionNames
   * @return <code>permsid=b_serverinstance_help_view permid=4353</code>
   */
  public TeamspeakResponse getByName(String... permissionNames) {
    return send(command("permidgetbyname").with("permsid", permissionNames));
  }
}
