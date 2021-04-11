import 'package:movie_app/actions/login.dart';
import 'package:movie_app/actions/register.dart';
import 'package:movie_app/models/states/auth_state.dart';
import 'package:redux/redux.dart';

Reducer<AuthState> authReducer = combineReducers(<Reducer<AuthState>>[
  TypedReducer<AuthState, LoginSuccessful>(_loginSuccessful),
  TypedReducer<AuthState, RegisterSuccessful>(_registerSuccessful),
  //TypedReducer<AppState, CreateProjectSuccessful>(_createProjectSuccessful),
]);

AuthState _loginSuccessful(AuthState state, LoginSuccessful action) {
  return state.rebuild((AuthStateBuilder b) => b.user = action.appUser.toBuilder());
}

AuthState _registerSuccessful(AuthState state, RegisterSuccessful action) {
  return state.rebuild((AuthStateBuilder b) => b.user = action.appUser.toBuilder());
}