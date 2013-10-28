type: post
status: published
title: Missing reflection methods ...
tags: code, informatique, java
date: Tue Apr 26 12:27:53 CEST 2005
~~~~~~
# Missing reflection methods ...

Although reflection is very powerfull, I've always found there are some leaks in code provided by Sun. So, here are some really minor additions to the java.lang.reflect package.First, a method that returns all field of the class, and of its superclass :[java]
public static Field[] getAllDeclaredFields(Class target) {

// Recursing

List temporary = new LinkedList();

if(target.getSuperclass()!=null) {


Field[] allFields = getAllDeclaredFields(target.getSuperclass());


temporary.addAll(Arrays.asList(allFields));

}

// Fetching all class fields

Field[] currentFields = target.getDeclaredFields();

temporary.addAll(Arrays.asList(currentFields));

return (Field[]) temporary.toArray(new Field[temporary.size()]);
}[/java]Believe me, this is really useful :-)Now, two complementary methods that are massively used by my {Beans-based persistence} :[java]
public static boolean[] setAllAccessible(Field[] allFields, boolean accessible) {

boolean[] access = new boolean[allFields.length];

Arrays.fill(access, accessible);

return setAllAccessible(allFields, access);
}
public static boolean[] setAllAccessible(Field[] allFields, boolean[] access) {

boolean[] previousAccess = new boolean[allFields.length];

for(int index=0; indexvia [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)