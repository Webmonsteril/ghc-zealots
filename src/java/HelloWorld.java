public class HelloWorld {

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Zealots Team");
        
        // <<<<<<<<<<<<<<<<<<<<<<<< TREES >>>>>>>>>>>>>>>>>>>>>>>>>>>
        List<String> examples = new ArrayList<String>();
        examples.add("(1,None,None)");
        examples.add("(1,None,(2,None,None))");
        examples.add("(1,(2,None,None),(3,None,None))");
        examples.add("(1,(2,(4,None,None),(5,None,None)),(3,None,None))");
        examples.add("(1,(2,(4,None,None),(5,None,None)),(3,(6,None,None),(7,None,None)))");
        for (String ex : examples) {
            TreeNode treeNode = createTreeFromString(ex);
            //printTreeLowMemory(treeNode);
            printTree(treeNode);
            Sytem.out.print(" Tree Height: " + findTreeHeight(treeNode));
        }
        
        
    }
    
    public static void printTree(TreeNode treeNode){
        Stack<TreeNode> nodes = new Stack<TreeNode>();
        nodes.add(treeNode);
        TreeNode current;
        System.out.println();

        while (!nodes.empty()){
            current = nodes.pop();
            System.out.print("(" + current.value + ",");
            if (current.right != null){
                nodes.add(current.right);
            } else {
                System.out.print("None,");
            }
            if (current.left != null){
                nodes.add(current.left);
            } else {
                System.out.print("None");
            }
            System.out.print(",");
            if (current.left == null && current.right == null){
                System.out.print(")");
            }
        }
    } 
    
    // Structure example: (1,(2,None,None),(3,None,None))
    public static TreeNode<Integer> createTreeFromString(String input){
        TreeNode<Integer> treeNode;
        if (input.equals(EMPTY)) {
            return null;
        }
        String[] subTrees = splitTree(input);

        treeNode = new TreeNode(Integer.valueOf(subTrees[0]));
        treeNode.left = createTreeFromString(subTrees[1]);
        treeNode.right = createTreeFromString(subTrees[2]);

        return treeNode;
    }

    public static String[] splitTree(String input){
        Stack<Object> parStack = new Stack<>();
        int separatorIndex = input.indexOf(',');
        String[] splitTree = new String[3];
        splitTree[0] = input.substring(1, separatorIndex); // value of node
        String leftChunk = input.substring(separatorIndex + 1);
        if (leftChunk.startsWith(EMPTY)){
            separatorIndex = leftChunk.indexOf(',');
            splitTree[1] = leftChunk.substring(0, separatorIndex); // left child node
            splitTree[2] = leftChunk.substring(separatorIndex + 1, leftChunk.length() - 1); // right child node
            return splitTree;
        }
        separatorIndex = 0;
        for (char c: leftChunk.toCharArray()){
            switch(c){
                case OPEN:
                    parStack.add(c);
                    break;
                case CLOSE:
                    parStack.pop();
                    break;
                default:
                    break;
            }
            separatorIndex++;
            if (parStack.empty()){
                break;
            }
        }
        // separatorIndex is now on ','
        splitTree[1] = leftChunk.substring(0, separatorIndex); // left child node
        splitTree[2] = leftChunk.substring(separatorIndex + 1, leftChunk.length() - 1); // right child node
        return splitTree;
    }

    public static class TreeNode<T> {
        public TreeNode left;
        public TreeNode right;
        public T value;

        public TreeNode(T value){
            this.value = value;
        }

        public TreeNode(TreeNode<T> treeNode){
            this.value = treeNode.value;
            this.left = treeNode.left;
            this.right = treeNode.right;
        }
    }

    public int findTreeHeight(TreeNode T) {
        if (T.left == null && T.right == null)
            return 0;
        if (T.left == null)
            return 1 + findTreeHeight(T.right);
        if (T.right == null)
            return 1 + findTreeHeight(T.left);

        return 1 + max(findTreeHeight(T.left), findTreeHeight(T.right));
    }
    
    public int max(int a, int b){
        return a > b ? a : b;
    }

}