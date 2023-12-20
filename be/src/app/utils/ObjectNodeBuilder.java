package app.utils;

import app.CommandRunner;
import com.fasterxml.jackson.databind.node.ObjectNode;


public final class ObjectNodeBuilder {
    /**
     * Builds an object node with the given parameters
     * @param command the command
     * @param username the username
     * @param timestamp the timestamp
     * @return the built object node
     */
    public static ObjectNode buildObjectNode(final String command,
                                             final String username, final int timestamp) {

        ObjectNode objectNode = CommandRunner.getObjectMapper().createObjectNode();
        objectNode.put("command", command);
        objectNode.put("user", username);
        objectNode.put("timestamp", timestamp);
        return objectNode;
    }
    /**
     * Builds an object node with the given parameters
     * @param command the command
     * @param timestamp the timestamp
     * @return the built object node
     */
    public static ObjectNode buildObjectNode(final String command, final int timestamp) {

        ObjectNode objectNode = CommandRunner.getObjectMapper().createObjectNode();
        objectNode.put("command", command);
        objectNode.put("timestamp", timestamp);
        return objectNode;
    }
    private ObjectNodeBuilder() {
    }
}
