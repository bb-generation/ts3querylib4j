package com.google.code.ts3query.model.manager;

import java.util.Iterator;
import java.util.List;

import com.google.code.ts3query.TeamspeakResponse;
import com.google.code.ts3query.model.Entity;
import com.google.code.ts3query.model.entity.File;
import com.google.code.ts3query.model.entity.FileTransfer;

public class FileTransfers extends Entity implements Iterable<FileTransfer> {

	/**
	 * Creates new directory in a channels file repository.
	 * 
	 * @param channelID
	 * @param channelPassword
	 * @param dirPath
	 */
	public void createDirectory(final long channelID, final String channelPassword, final String dirPath) {
		send(command("ftcreatedir").with("cid", channelID).with("cpw", channelPassword).with("dirname", dirPath));
	}

	/**
	 * Deletes one or more files stored in a channels file repository.
	 * 
	 * @param channelID
	 * @param channelPassword
	 * @param filePath
	 *            one more more file paths to delete
	 */
	public void deleteFile(final long channelID, final String channelPassword, final String... filePath) {
		// ftdeletefile cid={channelID} cpw={channelPassword} name={filePath}...
		send(command("ftdeletefile").with("cid", channelID).with("cpw", channelPassword).with("name", filePath));
	}

	/**
	 * Displays detailed information about one or more specified files stored in
	 * a channels file repository.
	 * 
	 * @param channelID
	 * @param channelPassword
	 * @param filePath
	 *            one more more file paths to get information about
	 */
	public TeamspeakResponse getFileInfo(final long channelID, final String channelPassword, final String... filePath) {
		// TODO this is tricky: first line contains extra "cid" and "path"
		//
		// cid=2 path=\/ name=Stuff size=0 datetime=1259415210 type=0|
		// name=Pic1.PNG size=563783 datetime=1259425462 type=1|
		// name=Pic2.PNG ...
		return send(command("ftgetfileinfo").with("cid", channelID).with("cpw", channelPassword).with("name", filePath));
	}

	/**
	 * Displays a list of files and directories stored in the specified channels
	 * file repository.
	 */
	public List<File> getFileList(final long channelID, final String channelPassword, final String filePath) {
		// TODO this is tricky: first line contains extra "cid" and "path"
		//
		// cid=2 path=\/ name=Stuff size=0 datetime=1259415210 type=0|
		// name=Pic1.PNG size=563783 datetime=1259425462 type=1|
		// name=Pic2.PNG ...
		return send(command("ftgetfilelist").with("cid", channelID).with("cpw", channelPassword).with("path", filePath))
				.asList(File.class, "cid", "path");
	}

	/**
	 * Displays a list of running file transfers on the selected virtual server.
	 * <p>
	 * The output contains the path to which a file is uploaded to, the current
	 * transfer rate in bytes per second, etc.
	 */
	public List<FileTransfer> getList() {
		return send(command("ftlist")).asList(FileTransfer.class);
	}

	/**
	 * Usage: ftrenamefile cid={channelID} cpw={channelPassword}
	 * [tcid={targetChannelID}] [tcpw={targetChannelPassword}]
	 * oldname={oldFilePath} ={newFilePath}
	 * 
	 * Renames a file in a channels file repository. If the two parameters tcid
	 * and tcpw are specified, the file will be moved into another channels file
	 * repository.
	 * 
	 * @param channelID
	 * @param channelPassword
	 * @param newFilePath
	 * @param targetChannelID
	 *            optional, <code>null</code> to skip
	 * @param targetChannelPassword
	 *            optional, <code>null</code> to skip
	 */
	public void renameFile(final long channelID, final String channelPassword, final String oldFilePath,
			final String newFilePath, final Long targetChannelID, final String targetChannelPassword) {
		send(command("ftrenamefile").with("cid", channelID).with("cpw", channelPassword).with("oldname", oldFilePath)
				.with("newname", newFilePath).with("tcid", targetChannelID).with("tcpw", targetChannelPassword));
	}

	/**
	 * Stops a running file transfer.
	 * 
	 * @param serverFileTransferID
	 * @param delete
	 *            <code>true</code> if the file should be deleted on the server
	 */
	public void stop(final long serverFileTransferID, final boolean delete) {
		send(command("ftstop").with("serverftfid", serverFileTransferID).with("delete", delete ? 1 : 0));
	}

	@Override
	public Iterator<FileTransfer> iterator() {
		return getList().iterator();
	}
}
