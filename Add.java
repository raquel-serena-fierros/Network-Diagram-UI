public class Add {
  
    private Node predecessor; 
    private Node destination;
    private int duration;

    public Node getPredecessor() {
        return predecessor;
    }

    public Node getDestination() {
        return destination;
    }

    public int getDuration() {
        return duration;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
}