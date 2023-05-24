package CarShop.Constants;

import javafx.scene.Node;
public enum MenuOptions {
    DASHBOARD(null),
    STORE(null),
    GARAGE(null),
    LOGIN(null),
    COMBIN(null);
    private Node node;
    private MenuOptions(Node node){
        this.node=node;
    }
    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
