import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class PostRepositoryByList implements PostRepository {

	private static final String FOLDER = "TMY-BLOG"; // 保存文件的文件夹名称
	private static final String SEPARATOR = "---"; // id和title的分隔符

	private static PostRepository postRepository;

	private static List<Post> posts = new ArrayList<Post>();

	public static PostRepository instance() {

		if (postRepository == null) {
			postRepository = new PostRepositoryByList();
		}

		return postRepository;
	}

	private PostRepositoryByList() {
	}

	@Override
	public void add(Post post) {
		posts.add(post);
	}

	@Override
	public Post getPostById(long id) {

		for (Post post : posts) {
			if (post.getId() == id) {
				return post;
			}
		}

		return null;
	}

	@Override
	public void remove(long id) {
		for (Post post : posts) {
			if (post.getId() == id) {
				posts.remove(post);
				return;
			}
		}
	}

	@Override
	public List<Post> getAll() {
		return posts;
	}

	@Override
	public void loadData() throws BlogAppException {
		File dir = new File(FOLDER);
		List<File> files = (List<File>) FileUtils.listFiles(dir, null, true);
		for (File f : files) {
			try {
				String fileName[] = f.getName().split("---");
				String content = FileUtils.readFileToString(f, "utf-8");

				Post post = new Post(fileName[1], content);
				post.setId(Long.parseLong(fileName[0]));

				postRepository.add(post);

			} catch (IOException e) {
				e.printStackTrace();
				BlogAppException be = new BlogAppException("load");
				be.initCause(e);
				throw be;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				BlogAppException be = new BlogAppException("load");
				be.initCause(e);
				throw be;
			}
		}
	}

	@Override
	public void saveData() throws BlogAppException {
		for (Post post : PostRepositoryByList.instance().getAll()) {
			String fileName = Long.toString(post.getId()) + SEPARATOR + post.getTitle();
			try {
				FileUtils.write(new File(FOLDER + File.separator + fileName), post.getContent(), "utf-8");
			} catch (IOException e) {
				e.printStackTrace();
				BlogAppException be = new BlogAppException("save");
				be.initCause(e);
				throw be;
			}
		}
	}

}