import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
public class Node {
    
    public Node() {
    	connected = false;
        point = new Point();
    }
    
    private int activityDuration;
    private String activityName; 
    private Point point; 
     
    public int predecessors = 0;
    public List <Node> children = new ArrayList<>();
    public List <Node> parents = new ArrayList<>();
    private boolean isHead = false;
    public boolean connected = false;
    private short processed = 0;
    
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }
    
    public void setActivityDuration(int activityDuration) {
        this.activityDuration = activityDuration;
    }

    public int getActivityDuration() {
        return activityDuration;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    
    public void setHead(boolean isHead) {
    	this.isHead = isHead; 
    }
    
    public boolean isTail() {
    	return (children.isEmpty());
    }
    
    public boolean isHead() {
    	return (parents.isEmpty());
    }
    
    public void add(Node n) {
    	children.add(n);
    }
    
    public void addParent(Node n) {
    	parents.add(n);
    }
    
    public void reset() {
    	processed = 0;
    }
    
    public void process() {
    	processed++;
    }
    
    public int getProcessed() {
    	return processed;
    }

    public boolean hasPredecessor() {
    	return predecessors != 0;
    }
    
    public int getPredecessors() {
    	return predecessors;
    }
    
    public void incPredecessors() {
    	predecessors++;
    }
    
    public boolean getHead() {
    	return isHead;
    }
    
    public boolean hasChildren() {
    	return (!children.isEmpty());
    }
    
    public boolean hasParents() {
    	return (!parents.isEmpty());
    }
    
    public void connect() {
    	connected = true;
    }
}