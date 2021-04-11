import 'package:movie_app/models/states/app_state.dart';
import 'package:movie_app/reducer/auth_reducer.dart';
import 'package:redux/redux.dart';

Reducer<AppState> reducer = combineReducers(<Reducer<AppState>>[
  _reducer,
]);

AppState _reducer(AppState state, dynamic action) {
  print(action);
  return state.rebuild((AppStateBuilder b) {
    b
      ..authState = authReducer(state.authState, action).toBuilder();
  });
}