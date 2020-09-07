//July 24, 2020
//Rashaad Ratliff-Brown

public class Astar {


    public static int [][] mainboard = new int [row][col]; 
    public static Node [][] node = new Node[row][col];
    public static Node start;
    public static Node goal;
    public static int row = 15;
    public static int col = 15;

    public static void main(String[]args)
    {
        Astard As = new Astard();
        As.set10percent(mainboard);
        As.writeToNode(mainboard, node);

        start = As.getStart(node);
        goal = As.getGoal(node);

        As.getHeuristic(node, goal);
        As.displayNode(node);

        As.displayHeuristic(node);

        start.setG(0);
        start.setH(node[start.getRow()][start.getCol()].getH());
        start.setF();

        Node [][] copy = new Node[row][col];

        for (int i = 0;i < node.length;i++)
        {
            for (int j = 0;j < node[0].length;j++)
            {
                copy[i][j] = node[i][j];
            }
        }

        boolean keepSearching = true;

        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();

        openList.add(start);
        System.out.println("Adding Node: " + start.toString()+" to the openList");

        while(keepSearching)
        {
            Node n = openList.remove(0);
            System.out.println("\nRemoving node " + n.toString()+" from openList\n");

            if(n.equals(goal))
            {
                System.out.println("Goal reached!. Path has been found");
                System.out.println("This is the path: ");
                keepSearching = false;

                while(!n.equals(start))
                {
                    System.out.print(n.getParent().toStringFinal());
                    n = n.getParent();

                    copy[n.getRow()][n.getCol()].setType(3);
                }
            }else
            {
                int Nrow = n.getRow();
                int Ncol = n.getCol();

                for(int i = Nrow - 1; i <= Nrow + 1; i++)
                {
                    for(int j = Ncol - 1; j <= Ncol + 1; j++)
                    {

                        if((i >= 0 && i < row) && (j >= 0 && j < row) && (i != Nrow || j != Ncol) && (node[i][j].getType() != 1))
                        {
                            Node newNode = new Node(i, j, 0);
                            System.out.println("Getting neighbors: " + newNode.toString());
                            newNode.setParent(n);
                            int newG = 10;
                            if(Math.abs(i - Nrow) + Math.abs(j - Ncol) == 2)
                            {
                                newG = 14;
                            }

                            newNode.setG(n.getG() + newG);
                            newNode.setH(node[start.getRow()][start.getCol()].getH());
                            newNode.setF();

                            if(As.checkinList(newNode, closedList) == null)
                            {
                                Node sm = As.checkinList(newNode, openList);
                                if(sm == null){
                                    openList.add(newNode);
                                    System.out.println("\nAdding Node : " + newNode.toString()+" to List\n");
                                }else{
                                    if(newNode.getG() < sm.getG()){
                                        sm.setG(newNode.getG());
                                        sm.setParent(n);
                                    }}}
                        }}
                }As.sort(openList); closedList.add(n);
            }}
        System.out.println("This is a grid layout where \"3\" represents the path\n");
        As.displayNode(copy);
    }
}