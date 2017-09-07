

public class Post {
    private long id;
    private String title;
    private String content;
    
    private static int count = 0;
    
    public Post(){
        id = count++; // count的类型为int，这里Java编译器会自动将int转换为long
    }
    
    public Post(String title, String content) {
        this.id = count++;
        this.title = title;
        this.content = content;
    }
    
    public void print() {
        System.out.println(this.title);
        System.out.println(this.content);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}