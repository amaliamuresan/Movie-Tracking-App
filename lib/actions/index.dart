abstract class AppAction {
  const AppAction();
}

abstract class ErrorAction implements AppAction {
  const ErrorAction();
}

typedef ActionResponse = void Function(AppAction action);