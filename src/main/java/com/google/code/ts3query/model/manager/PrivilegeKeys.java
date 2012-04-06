package com.google.code.ts3query.model.manager;

import java.util.Iterator;
import java.util.List;

import com.google.code.ts3query.model.Entity;
import com.google.code.ts3query.model.entity.Token;
import com.google.code.ts3query.option.TokenType;

public class PrivilegeKeys extends Entity implements Iterable<Token> {

  /**
   * Create a new token. If tokentype is set to 0, the ID specified with
   * tokenid1 will be a server group ID. Otherwise, tokenid1 is used as a
   * channel group ID and you need to provide a valid channel ID using tokenid2.
   * <p>
   * The tokencustomset parameter allows you to specify a set of custom client
   * properties. This feature can be used when generating tokens to combine a
   * website account database with a TeamSpeak user.
   * 
   * @param type
   * @param groupId
   *          (server|channel) groupID
   * @param channelId
   *          only in effect for {@link TokenType#channel}
   * @param description
   *          optional , <code>null</code> to skip
   * @param customFieldSet
   *          optional, <code>null</code> to skip
   * @return a new token
   */
  public String add(TokenType type, int groupId, int channelId, String description,
      Object customFieldSet) {
    return send(
        command("privilegekeyadd").with("tokentype", type.ordinal()).with("tokenid1", groupId)
            .with("tokenid2", channelId).with("tokendescription", description)
            .with("tokencustomset", customFieldSet)).getFirstResponse().get("token");
  }

  /**
   * Deletes an existing token matching the token key specified with token.
   * 
   * @param tokenKey
   */
  public void delete(String tokenKey) {
    send(command("privilegekeydelete").with("token", tokenKey));
  }

  /**
   * Use a token key gain access to a server or channel group. Please note that
   * the server will automatically delete the token after it has been used.
   * 
   * @param tokenKey
   */
  public void use(String tokenKey) {
    send(command("privilegekeyuse").with("token", tokenKey));
  }

  /**
   * Displays a list of tokens available including their type and group IDs.
   * Tokens can be used to gain access to specified server or channel groups.
   * <p>
   * A token is similar to a client with administrator privileges that adds you
   * to a certain permission group, but without the necessity of a such a client
   * with administrator privileges to actually exist. It is a long (random
   * looking) string that can be used as a ticket into a specific server group.
   */
  public List<Token> getList() {
    return send(command("privilegekeylist")).asList(Token.class);
  }

  @Override
  public Iterator<Token> iterator() {
    return getList().iterator();
  }
}
