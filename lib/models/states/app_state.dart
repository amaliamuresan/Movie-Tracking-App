
import 'package:built_value/built_value.dart';
import 'package:movie_app/models/states/auth_state.dart';
import 'package:movie_app/models/states/movies_state.dart';

part 'app_state.g.dart';

abstract class AppState implements Built<AppState, AppStateBuilder> {
  factory AppState.initialState() {
    return _$AppState((AppStateBuilder b) {
      b
        ..authState = AuthState.initialState().toBuilder()
        ..moviesState = MoviesState.initialState().toBuilder();
    });
  }

  factory AppState([void Function(AppStateBuilder) update]) = _$AppState;

  AppState._();

  @nullable
  AuthState get authState;

  @nullable
  MoviesState get moviesState;

}