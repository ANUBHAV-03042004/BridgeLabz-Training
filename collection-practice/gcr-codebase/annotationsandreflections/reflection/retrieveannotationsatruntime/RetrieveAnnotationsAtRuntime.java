package retrieveannotationsatruntime;
@Author(name="AKS")
public class RetrieveAnnotationsAtRuntime {
public static void main(String[] args) {
	Class <?> cls= RetrieveAnnotationsAtRuntime.class;
	if(cls.isAnnotationPresent(Author.class)) {
	Author authorname=cls.getAnnotation(Author.class);
	System.out.println("Author name :"+ authorname.name());
	}
}
}
