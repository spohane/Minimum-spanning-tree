package apps;

import structures.*;
import java.util.ArrayList;


public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
				PartialTreeList finalPartialTreeList;
				finalPartialTreeList = new PartialTreeList();
				MinHeap myMinHeap;
				PartialTree.Arc myArc ;
				
				for (int i = 0 ;i<graph.vertices.length;i++)
				{
					PartialTree myPartialTree = new PartialTree(graph.vertices[i]);
					PartialTree bigDaddyPartialTree  = new PartialTree(graph.vertices[i]);
					for (Vertex.Neighbor nbr =graph.vertices[i].neighbors; nbr != null; nbr=nbr.next) 
				    {
				        myArc = new PartialTree.Arc((Vertex)myPartialTree.getRoot(), nbr.vertex, nbr.weight);
				        myMinHeap = myPartialTree.getArcs();
				        myMinHeap.insert(myArc);   
				    }
					bigDaddyPartialTree.merge(myPartialTree);
					finalPartialTreeList.append(bigDaddyPartialTree);
				}
				return finalPartialTreeList;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param graph Graph for which MST is to be found
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(Graph graph, PartialTreeList ptlist) {
		   PartialTree.Arc myArcAlpha=null, myArcTemp ;
           PartialTree PTY=null,PTX = null ,PQX = null  , tempoPartialTree;
           ArrayList<PartialTree.Arc> list = new ArrayList<PartialTree.Arc>();
           ArrayList<PartialTree.Arc> mstlist = new ArrayList<PartialTree.Arc>();
           char found='F' ,FOUNDINPTX='T';
       while (ptlist.size()>1)
           { 
           PTX = ptlist.remove();
           PQX=    PTX;        
           FOUNDINPTX='T';
           
       while(found=='F')
       {
           while( FOUNDINPTX=='T')
           {
               myArcAlpha = PQX.getArcs().deleteMin();
           String Str1=PTX.toString(),Str2=myArcAlpha.v2.toString(), Str3="("+Str2;
             if (Str1.contains(Str3))                                
                 FOUNDINPTX='T';
             else
             FOUNDINPTX='F';
           }
             FOUNDINPTX='T';
           
           mstlist.add(myArcAlpha);
            PTY= ptlist.removeTreeContaining(myArcAlpha.v2);
           
           if (PTY!=null)
           {
           PQX.merge(PTY);
           found='T';
           ptlist.append(PQX);
           }
           /*else
           {
               myArcAlpha = PQX.getArcs().deleteMin();
           mstlist.add(myArcAlpha);
           PTY= ptlist.removeTreeContaining(myArcAlpha.v2);
           }*/
           }
       found='F';
   }             
       PartialTree myTmpPartialTree2;
       myTmpPartialTree2 = ptlist.remove();       
   
    
       return mstlist;
   
   }
}

