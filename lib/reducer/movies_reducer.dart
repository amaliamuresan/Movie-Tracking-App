import 'package:movie_app/actions/get_movies.dart';
import 'package:movie_app/actions/get_movies_by_name.dart';
import 'package:movie_app/actions/update_genre.dart';
import 'package:movie_app/actions/view_movie.dart';
import 'package:movie_app/models/states/movies_state.dart';
import 'package:redux/redux.dart';

Reducer<MoviesState> moviesReducer = combineReducers(<Reducer<MoviesState>>[
  TypedReducer<MoviesState, GetMoviesSuccessful>(_getMoviesSuccessful),
  TypedReducer<MoviesState, GetMoviesByNameSuccessful>(
      _getMoviesByNameSuccessful),
  TypedReducer<MoviesState, UpdateGenre>(_updateGenre),
  TypedReducer<MoviesState, ViewMovieSuccessful>(_viewMovieSuccessful),
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

MoviesState _getMoviesByNameSuccessful(
    MoviesState state, GetMoviesByNameSuccessful action) {
  return state.rebuild((MoviesStateBuilder b) => b
    ..movies = action.movies.toBuilder()
    ..page = 0);
}

MoviesState _viewMovieSuccessful(MoviesState state, ViewMovieSuccessful action) {
  return state
      .rebuild((MoviesStateBuilder b) => b.movie = action.movie.toBuilder());
}
