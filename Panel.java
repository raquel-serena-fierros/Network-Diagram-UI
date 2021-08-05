import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Panel extends javax.swing.JPanel {
    
    static public abstract class NodeCreator {
        public abstract Node createNode( Point point );
    }

    boolean flag = false;

    Node predecessor;
    Node destination;
    
    private List<Node> nodes;
    private List<Add> connect;
    
    Processor pro = new Processor(nodes);
    Font nodeFont;
    
    /**PANEL**/
    public Panel( List<Node> nodes, List<Add> connects, final NodeCreator creator ) {
        super();

        // nodeFont is reusable once instantiated since it is an object.
        nodeFont = new Font( "Verdana", Font.BOLD, 16 );
        
        predecessor = null;
        destination = null;

        this.nodes = nodes;
        this.connect = connects;
        
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e){ 
                if ( e.getButton() != MouseEvent.BUTTON1 ) return;

                if( getNodeXY( e.getPoint() ) == null ){
                    Node n = creator.createNode( e.getPoint() );
                    if( n != null ){ repaint(); }
                }
            } 

            public void mouseClicked(MouseEvent e) { 
                if (e.getButton() != MouseEvent.BUTTON3) return;

                if( !flag ){
                    predecessor = getNodeXY( e.getPoint() );
                    flag = ( predecessor != null );
                    if( flag ) repaint(); // only repaint if updated
                } else {
                    destination = getNodeXY( e.getPoint() );

                    if (destination != null && destination != predecessor ){
                        Add a = new Add();
                        a.setPredecessor( predecessor );
                        a.setDestination( destination );

                        // Sets up some data for the processor.
                        predecessor.add( destination ); 
                        destination.addParent(predecessor);
                        destination.incPredecessors();      

                        connect.add(a);
                    }

                    flag = false;
                    predecessor = null;
                    destination = null;
                    repaint();
                }
            } 
        }); 
    }
   
    public void paint( Graphics g ){
        super.paint(g);
        // Drawing Nodes
        g.setFont( nodeFont ); // only need to set the graphics font once, it's cached
        for (Node node : nodes ) {
            drawNode( g, node );
        }
        
        // redraw the selected node to be highlighted
        if( flag && predecessor != null ){
            drawNode( g, predecessor, Color.yellow );
        }
        
        // drawing Arrows        
        for( Add adder : connect ){ 
            drawConnection( g, adder );
        }
    }
   
    public Node getNodeXY( Point point ){        
        // find node
        for ( Node node : nodes ) {
            if( pointInCircle( point, node.getPoint(), 50 ) ) return node;
        }
        return null;
    }

    public Node getNodeXY(int x, int y) {
        return getNodeXY( new Point( x, y ) );
    }

    private boolean pointInCircle( Point point, Point pos, int rad ){
        return (( point.x >= pos.x )  && 
                ( point.x <= pos.x + rad ) && 
                ( point.y >= pos.y ) &&  
                ( point.y <= pos.y + rad ) );
    }

    // draws a node, provided a node
    private void drawNode( Graphics g, Node node ){
        drawNode( g, node, Color.white );
    }
    private void drawNode( Graphics g, Node node, Color fill ){
        Point point = node.getPoint();
        
        g.setColor( fill );
        g.fillOval( point.x, point.y, 50, 50 );

        // Inner text
        g.setColor( Color.BLACK );
        g.drawString( node.getActivityName(), point.x + 20, point.y + 25 );
        g.drawString( String.valueOf( node.getActivityDuration() ), point.x + 20, point.y + 43 );
        
        // Outline
        g.setColor( Color.BLUE );
        g.drawOval( point.x, point.y, 50, 50 );
    }

    // Draws the arrows between connected elements
    private void drawConnection( Graphics g, Add adder  ){
        Point p1 = new Point( adder.getDestination().getPoint() );
        Point p2 = new Point( adder.getPredecessor().getPoint() );

        // 15 is the angle seperation, so 30 from head to head
        double angleSeparation = Math.toRadians( 180 - 15 );
        int size = 15;

        // center of each node, will be the edges later.
        Point point1 = new Point( p2.x + 25, p2.y + 25);
        Point point2 = new Point( p1.x + 25, p1.y + 25);

        // angle from origin to destination
        double angle = Math.atan2( point2.y-point1.y, point2.x-point1.x );
        
        // shift the line to the edges of the circles
        point1.x += Math.cos( angle ) * 27.45;
        point1.y += Math.sin( angle ) * 27.45;
        point2.x -= Math.cos( angle ) * 27.45;
        point2.y -= Math.sin( angle ) * 27.45;
    
        // Defining the arrow tips on destination
        p1.x = (int)(point2.x + size*Math.cos( -angle - angleSeparation ) );
        p1.y = (int)(point2.y - size*Math.sin( -angle - angleSeparation ) );
        p2.x = (int)(point2.x + size*Math.cos( -angle + angleSeparation ) );
        p2.y = (int)(point2.y - size*Math.sin( -angle + angleSeparation ) );

        // color of arrow line
        g.setColor(Color.RED);
        
        ((Graphics2D)g).setStroke(new BasicStroke(2.5f));
        
        // Draw line and arrow-tip
        g.drawLine( point1.x, point1.y, point2.x, point2.y );
        g.drawLine( p1.x, p1.y, point2.x, point2.y );
        g.drawLine( p2.x, p2.y, point2.x, point2.y );
    }
}