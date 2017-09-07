

import java.util.List;

public interface PostRepository {
    
    public void add(Post post);
    
    public Post getPostById(long id);
    
    public void remove(long id);
    
    public List<Post> getAll();  
    
    public void loadData() throws BlogAppException;
    
    public void saveData() throws BlogAppException;  
}