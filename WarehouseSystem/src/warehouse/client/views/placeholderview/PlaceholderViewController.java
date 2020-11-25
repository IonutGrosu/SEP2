package warehouse.client.views.placeholderview;

import warehouse.client.core.ViewHandler;

public class PlaceholderViewController
{
  private PlaceholderViewModel placeholderViewModel;
  private ViewHandler viewHandler;

  public void init(PlaceholderViewModel placeholderViewModel, ViewHandler viewHandler)
  {
    this.placeholderViewModel = placeholderViewModel;
    this.viewHandler = viewHandler;
  }
}
