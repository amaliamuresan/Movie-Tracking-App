import 'package:movie_app/actions/add_watched.dart';
import 'package:movie_app/actions/follow_user.dart';
import 'package:movie_app/actions/login.dart';
import 'package:movie_app/actions/register.dart';
import 'package:movie_app/actions/to_watch.dart';
import 'package:movie_app/models/states/auth_state.dart';
import 'package:redux/redux.dart';

Reducer<AuthState> authReducer = combineReducers(<Reducer<AuthState>>[
  TypedReducer<AuthState, LoginSuccessful>(_loginSuccessful),
  TypedReducer<AuthState, RegisterSuccessful>(_registerSuccessful),
  TypedReducer<AuthState, ToWatchSuccessful>(_toWatchSuccessful),
  TypedReducer<AuthState, AddWatchedSuccessful>(_addWatchedSuccessful),
  TypedReducer<AuthState, FollowUserSuccessful>(_followUserSuccessful),
]);

AuthState _loginSuccessful(AuthState state, LoginSuccessful action) {
  return state.rebuild((AuthStateBuilder b) => b.user = action.appUser.toBuilder());
}

AuthState _registerSuccessful(AuthState state, RegisterSuccessful action) {
  return state.rebuild((AuthStateBuilder b) => b.user = action.appUser.toBuilder());
}

AuthState _toWatchSuccessful(AuthState state, ToWatchSuccessful action) {
  return state.rebuild((AuthStateBuilder b) => (!b.user.toWatchMovies.build().toList().contains(action.movieId))
      ? b.user.toWatchMovies.add(action.movieId)
      : b.user.toWatchMovies.remove(action.movieId));
}

AuthState _addWatchedSuccessful(AuthState state, AddWatchedSuccessful action) {
  return state.rebuild((AuthStateBuilder b) => (!b.user.watchedMovies.build().toList().contains(action.movieId))
      ? b.user.watchedMovies.add(action.movieId)
      : b.user.watchedMovies.remove(action.movieId));
}

AuthState _followUserSuccessful(AuthState state, FollowUserSuccessful action) {
  return state.rebuild((AuthStateBuilder b) => (!b.user.friends.build().toList().contains(action.uid))
      ? b.user.friends.add(action.uid)
      : b.user.friends.remove(action.uid));
}
