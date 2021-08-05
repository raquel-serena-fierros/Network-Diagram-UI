import java.util.List;

import javax.swing.JOptionPane;

import java.util.ArrayList;

public class Processor {
	public List<Node> nodes;
	public List<Path> paths;
	public List<Node> heads;

	private boolean didFail = false;
	private String failureString = "";
	
	Processor( List<Node> nodes ){
		this.nodes = nodes;
		paths = new ArrayList<>();
		heads = new ArrayList<>();
	}
	
	private void buildHeads() {
		for (int i = 0; i < nodes.size(); i++) {
			if(!nodes.get(i).hasPredecessor()) {
				heads.add((nodes.get(i)));
			}
		}
	}
	
	/**CONNECTION METHODS**/
	public boolean isConnected() {
		resetConnection();
		if (nodes.isEmpty())
			return true;
		if(nodes.size()==1)
			return true;
		else {
			ConnectionBomb(nodes.get(0));
			for (int i = 0; i < nodes.size(); i++) {
				if (nodes.get(i).connected==false) {
					
					return false;
				}
			}
			return true;
		}
	}
	private void ConnectionBomb(Node n) {
		n.connect();
		for (int i = 0; i < n.children.size(); i++) {
			if(n.children.get(i).connected==false) {
				ConnectionBomb(n.children.get(i));
				}
		}
		for (int i = 0; i < n.parents.size(); i++) {
			if(n.parents.get(i).connected==false) {
				ConnectionBomb(n.parents.get(i));
				}
		}	
	}
	private void resetConnection() {
		for (Node node : nodes)
			node.connected=false;
	}
	
	/**End connection methods**/
	
	public boolean validHead() {
		return !heads.isEmpty();
	}
	
	public String outputString() {
		String outputString = "";
		for(int i = 0; i < paths.size(); i++) {
			outputString+=paths.get(i).toString();
		}
		return outputString;
	}
	
	public void buildPaths() {
		if(!isConnected()) {
			didFail = true;
			failureString = "Not connected.";
			return;
		
		} else {
			buildHeads();
			if( !validHead() ){
				System.out.println("no heads.");
				didFail = true;
				failureString = "Invalid head.";

				return;

			} else {

				didFail = false;
				failureString = "";
				paths.clear();
				
				for ( Node head : heads ) buildPathsAlg( head );
			}
		}
	}
	
	public void buildPathsAlg(Node head) {
		Path p = new Path();
		p.add(head);
		if (head.hasChildren()) {
			for(int i = 0; i < head.children.size(); i++) {
				buildPathsAlgHelper(head.children.get(i), p);
			}
		}
		else {
			paths.add(p);
		}
		
	}
	
	public void buildPathsAlgHelper(Node child, Path p) {
		Path pa = new Path();
		pa.copyFrom(p);
		pa.add(child);

		if (!pa.loopCheck()) {
			didFail = true;
			failureString = "There is a loop.";
			return;
		}
			
		if (child.isTail())
			paths.add(pa);
		else {
			for (int i = 0; i < child.children.size(); i++) {
				buildPathsAlgHelper(child.children.get(i), pa);
			}
		}
		pathsSort();
		
	}
	
	public void pathsSort() {
		Path key = new Path();
		for (int i = 0; i < (paths.size()-1); i++) {
			int max = i;
			for (int j = i; j < paths.size();j++) {
				if (paths.get(j).getDuration()>paths.get(max).getDuration())
					max = j; 
			}
			key.swapPath(paths.get(max));
			paths.get(max).swapPath(paths.get(i));
			paths.get(i).swapPath(key);
		}
	}
	
	
	public boolean failed(){
		return didFail;
	}

	public String failureMessage(){
		return failureString;
	}
	
	public String crits() {
		String crits= "";
		int max = findMax();
		for (int i = 0; i < paths.size(); i++) {
			if (paths.get(i).getDuration()==max)
				crits += paths.get(i).toString();
		}
		return crits;
	}
	
	public int findMax() {
		int max = paths.get(0).getDuration();
		for (int i = 1; i < paths.size();i++) {
			if (paths.get(i).getDuration()>max)
				max = paths.get(i).getDuration();
		}
		return max;
	}
	
}