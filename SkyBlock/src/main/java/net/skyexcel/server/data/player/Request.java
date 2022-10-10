package net.skyexcel.server.data.player;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Consumer;

public class Request {


    private List<Action> action;

    public void newRequest() {

        Action newAction = new Action();
        action.add(newAction);
    }

    public class Action {
        private Consumer<Node> acceptAction;
        private Consumer<Node> denyAction;

        private Node node;


        public void setNode(Node node) {
            this.node = node;
        }

        public void accept(Consumer<Node> action) {
            this.acceptAction = action;

            Node node = new Node();

        }

        public void deny(Consumer<Node> action) {
            this.denyAction = action;
        }

        public Node getNode() {
            return node;
        }
    }

    public class Node {
        private Player player;

        public void setPlayer(Player player) {
            this.player = player;
        }

        public void remove() {
            player = null;
        }

        public Player getPlayer() {
            return player;
        }
    }
}
