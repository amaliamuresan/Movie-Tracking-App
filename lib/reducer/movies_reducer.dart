import 'package:movie_app/actions/get_movies.dart';
import 'package:movie_app/models/states/movies_state.dart';
import 'package:redux/redux.dart';

Reducer<MoviesState> moviesReducer = combineReducers(<Reducer<MoviesState>>[
  TypedReducer<MoviesState, GetMoviesSuccessful>(_getMoviesSuccessful),
]);

MoviesState _getMoviesSuccessful(
    MoviesState state, GetMoviesSuccessful action) {
  return state.rebuild((MoviesStateBuilder b) =>
  b
    ..movies = action.movies.toBuilder()
    ..page = b.page + 1);
}
