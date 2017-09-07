
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
		// your code here:显示博客列表
		for (Post post : postRepository.getAll()) {
			String fileName = Long.toString(post.getId()) + ":" + post.getTitle();
			System.out.println(fileName);
		}

	}

	private static void processShowCommand() {
		// your code here:从输入中读取博客id，并将它显示出来，注意处理博客不存在的情�?		System.out.println("please input blog id");
		String id = scanner.nextLine();
		if (postRepository.getPostById(Long.parseLong(id)) == null) {
			System.out.println("博客不存�?);

		} else {
			postRepository.getPostById(Long.parseLong(id)).print();
		}
	}

	private static void processDeleteCommand() {
		// your code here:从输入中读取博客id，并将它删除
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
					// 处理创建博客的逻辑
					processCreateCommand();

				} else if (command.equals("list")) {
					// 处理显示博客列表的逻辑
					processListCommand();

				} else if (command.equals("show")) {
					// 处理显示博客内容的逻辑
					processShowCommand();
				} else if (command.equals("delete")) {
					// 处理删除博客的逻辑
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