import 'package:meta/meta.dart';
import 'package:movie_app/actions/index.dart';
import 'package:movie_app/actions/login.dart';
import 'package:movie_app/actions/register.dart';
import 'package:movie_app/data/api.dart';
import 'package:movie_app/models/states/app_state.dart';
import 'package:movie_app/models/app_user.dart';
import 'package:redux_epics/redux_epics.dart';
import 'package:rxdart/rxdart.dart';

class AuthEpics {
  const AuthEpics({@required AppApi api})
      : assert(api != null),
        _api = api;

  final AppApi _api;

  Epic<AppState> get epics {
    return combineEpics(<Epic<AppState>>[
      TypedEpic<AppState, Login$>(_login),
      TypedEpic<AppState, Register$>(_register),
    ]);
  }

  Stream<AppAction> _login(Stream<Login$> actions, EpicStore<AppState> store) {
    //print('Am ajuns in apic login');
    return actions //
        .flatMap((Login$ action) => Stream<Login$>.value(action)
            .asyncMap((Login$ action) =>
                _api.login(email: action.email, password: action.password))
            .expand((AppUser user) => <AppAction>[
                  Login.successful(user),
                ])
            .onErrorReturnWith((dynamic error) => Login.error(error))
            .doOnData(action.response));
  }

  Stream<AppAction> _register(
      Stream<Register$> actions, EpicStore<AppState> store) {
    return actions //
        .flatMap((Register$ action) => Stream<Register$>.value(action)
            .asyncMap((Register$ action) => _api.register(
                  email: action.email,
                  password: action.password,
                  displayName: action.displayName,
                ))
            .expand((AppUser user) => <AppAction>[
                  Register.successful(user),
                ])
            .onErrorReturnWith((dynamic error) => Register.error(error))
            .doOnData(action.response));
  }
}
