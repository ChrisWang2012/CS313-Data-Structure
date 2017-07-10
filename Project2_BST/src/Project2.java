import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Project2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader fileReader = null;
		BufferedReader br = null;
		try {
			BinarySearchTree<Integer> bTree = new BinarySearchTree<Integer>();
			fileReader = new FileReader(new File("project2.txt"));// read from file"project2.txt"
			br = new BufferedReader(fileReader);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.trim().equals("") || line.startsWith("#")) {
					continue;
				}
				if (line.startsWith("insert")) {// insert element
					String value = line.substring(7);//read the number to be inserted 
					bTree.insert(new Integer(value));
				} else if (line.startsWith("remove")) {// remove element
					String value = line.substring(7);//read the number to be removed 
					bTree.remove(new Integer(value));
				} else if (line.startsWith("print_tree")) {// print tree
					bTree.printTree();
				} else if (line.startsWith("inorder_list")) { // print inorder list
					Iterator<Integer> it = bTree.iterator();
					while (it.hasNext()) {//go through the nodes, check the value, make them in order and print 
						Integer value = (Integer) it.next();
						System.out.print(value + " ");
					}
					System.out.println();
				} else {//unknown command
					System.out.println("Unknow command: " + line);
				}

			}
		} catch (

		FileNotFoundException e)

		{
			e.printStackTrace();
		} catch (

		IOException e)

		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{
			try {
				if (br != null) {
					br.close();
				}
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
