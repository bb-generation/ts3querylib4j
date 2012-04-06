package com.google.code.ts3query.model.manager;

import java.util.Iterator;
import java.util.List;

import com.google.code.ts3query.model.Entity;
import com.google.code.ts3query.model.entity.Channel;
import com.google.code.ts3query.model.entity.ServerGroup;
import com.google.code.ts3query.model.entity.Token;
import com.google.code.ts3query.option.TokenType;

public class Tokens extends Entity implements Iterable<Token> {

	/**
	 * Displays a list of tokens available including their type and group IDs.
	 * Tokens can be used to gain access to specified server or channel groups.
	 */
	public List<Token> getList() {
		return send(command("tokenlist")).asList(Token.class);
	}

	@Override
	public Iterator<Token> iterator() {
		return getList().iterator();
	}

	public Token getToken(String tokenKey) {
		for (Token token : getList()) {
			if (token.getToken().equals(tokenKey)) {
				return token;
			}
		}
		return null;
	}

	public String addToken(ServerGroup serverGroup) {
		return add(TokenType.servergroup, serverGroup.getId(), 0);
	}

	public String addToken(Channel channel) {
		// TODO is PID == CGID?
		return add(TokenType.channel, channel.getPid(), channel.getId());
	}

	/**
	 * Create a new token. If tokentype is set to 0, the ID specified with
	 * tokenid1 will be a server group ID. Otherwise, tokenid1 is used as a
	 * channel group ID and you need to provide a valid channel ID using
	 * tokenid2.
	 * 
	 * @param tokentype
	 * @param groupId
	 *            (server|channel) groupID
	 * @param channelId
	 *            only in effect for {@link TokenType#channel}
	 * 
	 * @return <code>token=eKnFZQ9EK7G7MhtuQB6+N2B1PNZZ6OZL3ycDp2OW</code>
	 */
	private String add(TokenType type, int groupId, int channelId) {
		return send(
				command("tokenadd").with("tokentype", type.ordinal()).with("tokenid1", groupId).with("tokenid2",
						channelId)).getFirstResponse().get("token");
	}

	/**
	 * Deletes an existing token matching the token key specified with token.
	 * 
	 * @param tokenKey
	 */
	public void delete(String tokenKey) {
		send(command("tokendelete").with("token", tokenKey));
	}

	/**
	 * Use a token key gain access to a server or channel group. Please note
	 * that the server will automatically delete the token after it has been
	 * used.
	 * 
	 * @param tokenKey
	 */
	public void use(String tokenKey) {
		send(command("tokenuse").with("token", tokenKey));
	}

}
