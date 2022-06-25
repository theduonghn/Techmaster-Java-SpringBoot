package vn.techmaster.jobhunt.model;

public enum EnumSkill {
  Java("Java"),
  CSharp("Csharp"),
  AWS("AWS"),
  SQL("SQL");

  public final String label;

  private EnumSkill(String label) {
    this.label = label;
  }
}
