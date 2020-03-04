package org.imperfectmommy.rexxeditor.contentoutliner;

/**
 * A class storing a REXX content element to be displayed in the content
 * outliner.
 * <p>
 * This element stores a name, an offset relative to the beginning of a document
 * in characters, a length and a category.
 * </p>
 * 
 * @author gulasch
 */
public class RexxContentElement {
    /**
     * The name of the content element.
     */
    protected String              name;
    protected int                 offset;
    protected int                 length;
    protected RexxContentCategory category;

    /**
     * Constructor for a content element used in the outliner.
     * 
     * @param name     The name to display in the content outliner.
     * @param offset   The offset in the document.
     * @param length   The length of the element.
     * @param category The element category that is diplayed in the content
     *                 outliner. Should be either METHOD, CLASS, REQUIRES or
     *                 VARIABLE.
     */
    public RexxContentElement(String name, int offset, int length, RexxContentCategory category) {
        this.name     = name;
        this.offset   = offset;
        this.length   = length;
        this.category = category;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RexxContentCategory getCategory() {
        return this.category;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String toString() {
        return "Category: " + this.category + ". Name: " + this.name;
    }
}
