import 'package:movie_app/actions/get_movies.dart';
import 'package:movie_app/actions/update_genre.dart';
import 'package:movie_app/models/states/movies_state.dart';
import 'package:redux/redux.dart';

Reducer<MoviesState> moviesReducer = combineReducers(<Reducer<MoviesState>>[
  TypedReducer<MoviesState, GetMoviesSuccessful>(_getMoviesSuccessful),
  TypedReducer<MoviesState, UpdateGenre>(_updateGenre),
]);

MoviesState _getMoviesSuccessful(
    MoviesState state, GetMoviesSuccessful action) {
  return state.rebuild((MoviesStateBuilder b) => b
    ..movies = action.movies.toBuilder()
    ..page = b.page + 1);
}

MoviesState _updateGenre(MoviesState state, UpdateGenre action) {
  return state.rebuild((MoviesStateBuilder b) => b
    ..movies.clear()
    ..genre = action.genre
    ..page = 1);
}
