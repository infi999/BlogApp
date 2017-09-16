
public class BlogApp {

	private static Scanner scanner;

	private static final String END_OF_POST = ":wq";

	private static PostRepository postRepository = PostRepositoryByList.instance();

	private static void processCreateCommand() {
		System.out.println("please input blog title");
		String title = scanner.nextLine();
		System.out.println("please input blog content ended with ':wq'");

		StringBuffer content = new StringBuffer();

		while (true) {

			String word = scanner.nextLine();

			if (word.equals(END_OF_POST)) {
				break;
			}

			content.append(word);
			content.append("\n");
		}

		Post post = new Post(title, content.toString());
		post.print();
		postRepository.add(post);
	}

	private static void processListCommand() {
		
		for (Post post : postRepository.getAll()) {
			String fileName = Long.toString(post.getId()) + ":" + post.getTitle();
			System.out.println(fileName);
		}

	}

	private static void processShowCommand() {
		System.out.println("please input blog id");
		String id = scanner.nextLine();
		if (postRepository.getPostById(Long.parseLong(id)) == null) {
			System.out.println("åšå®¢ä¸å­˜åœ?);

		} else {
			postRepository.getPostById(Long.parseLong(id)).print();
		}
	}

	private static void processDeleteCommand() {
		
		System.out.println("please input blog id");
		String id = scanner.nextLine();
		postRepository.remove(Long.parseLong(id));

	}

	public static void main(String[] args) {
		scanner = new Scanner(System.in);

		try {

			while (true) {
				String command = scanner.nextLine();
				System.out.println(command);

				if (command.equals("exit")) {
					break;
				} else if (command.equals("create")) {
					
					processCreateCommand();

				} else if (command.equals("list")) {
					
					processListCommand();

				} else if (command.equals("show")) {
					
					processShowCommand();
				} else if (command.equals("delete")) {
					
					processDeleteCommand();
				} else if (command.equals("save")) {
					postRepository.saveData();
				} else if (command.equals("load")) {
					postRepository.loadData();
				} else {
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
