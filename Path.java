import java.util.List;
import java.util.ArrayList;

public class Path {
	
	public List <Node> path;
	private int duration;
	private String toString;
	
	public Path() {
		path = new ArrayList<>();
	}
	
	public void add(Node n) {
		path.add(n);
	}
	
	public Node getNode(int i) {
		return path.get(i);
	}
	
	public boolean loopCheck() {
		if (path.isEmpty()||path.size()==1) {
			return true;
		}
		for (int i = 0 ; i < path.size(); i++) {
			for (int j = i+1; j < path.size();j++) {
				if (path.get(i)==path.get(j))
					return false;
			}
		}
		return true;
	}
	
	public int getDuration() {
		int duration = 0;
		for (int i = 0; i < path.size(); i++) {
			duration+=path.get(i).getActivityDuration();
		}
		return duration;
	}
	
	private String pathString() {
		String pathString = "";
		for (int i = 0; i < path.size(); i++) {
			if (i!=path.size()-1)
				pathString+=(path.get(i).getActivityName()+"-->");
			else
				pathString+=(path.get(i).getActivityName());
		}
		return pathString;
	}
	
	public int size() {
		return path.size();
	}
	
	public void copyFrom(Path p) {
		for (int i = 0; i < p.size(); i++) {
			path.add(p.getNode(i));
		}
	}
	
	public void swapPath(Path p) {
		Path temp = new Path();
		temp.path = this.path;
		this.path = p.path;
		p.path = this.path;
	}
	
	public String toString() {
		return ("\nPath: "+pathString()+"\n Duration: "+getDuration());
	}
}