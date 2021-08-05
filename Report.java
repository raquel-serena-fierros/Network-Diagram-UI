import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import java.lang.StringBuilder;
import java.time.LocalDateTime;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

// Report class which allows you to write a network diagram report.

public class Report{

    public Report( List<Node> nodes, Processor processor ){
        
        this.nodes = new ArrayList<>( nodes );
        this.processor = processor;

    }

    public boolean write( File file ){
        try( BufferedWriter writer = new BufferedWriter( new FileWriter(file) ) ){
            
            StringBuilder sb = new StringBuilder();

            sb.append( "Title:\t")
                .append( file.getName().replace(".txt", "") )
                .append( SEP )
                .append( "Date:\t" )
                .append( LocalDateTime.now() )
                .append( SEP ).append( SEP );
            
            // Display activities in alphabetical order 
            sb.append( "Activities:" )
                .append( SEP );

            Collections.sort( nodes, sorter );
            
            for( Node node : nodes ){
                sb.append( node.getActivityName() )
                    .append( " : " )
                    .append( node.getActivityDuration() )
                    .append( SEP );
            }

            // Display paths
            sb.append( SEP ).append( SEP ).append( "Paths:" );
            if( processor == null || processor.failed() ){
                sb.append( " THERE ARE NO PATHS." );
            } else {
            	processor.pathsSort();
                for (int i = 0; i < processor.paths.size(); i++) {
                	sb.append( SEP )
                	.append(processor.paths.get(i).toString());
                }
            }
            
            writer.append( sb );
            return true;
        } 
        catch( IOException _E ) {
            return false;
        }

    }

    private List<Node> nodes;
    private Processor processor;
    final static private String SEP = System.getProperty( "line.separator" ); 

    static final private Comparator sorter = new Comparator<Node>() {
        public int compare( Node L, Node R ) {
            
            String lName = L.getActivityName().toLowerCase();
            String rName = R.getActivityName().toLowerCase();
            
            return lName.compareTo( rName );
        }
    };
}