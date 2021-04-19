import 'package:built_collection/built_collection.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:movie_app/actions/get_movies.dart';
import 'package:movie_app/actions/get_movies_by_name.dart';
import 'package:movie_app/actions/update_genre.dart';
import 'package:movie_app/actions/view_movie.dart';
import 'package:movie_app/containers/genre_container.dart';
import 'package:movie_app/containers/movies_container.dart';
import 'package:movie_app/models/movie.dart';
import 'package:movie_app/models/states/app_state.dart';
import 'package:movie_app/routes/routes.dart';

// ignore: must_be_immutable
class HomePage extends StatelessWidget {
  String searchedMovie = '';
  ScrollController controller = ScrollController();

  @override
  Widget build(BuildContext context) {
    List<String> genres = [
      'All',
      'Drama',
      'Romance',
      'Crime',
      'TV Movie',
      'Science Fiction',
      'Mystery',
      'War',
      'Family',
      'Music',
      'Thriller',
      'Documentary',
      'Comedy',
      'Fantasy',
      'History',
      'Action'
    ];

    return Scaffold(
      appBar: AppBar(
        title: TextFormField(
          validator: (val) => val.isEmpty ? 'Enter a valid name' : null,
          style: TextStyle(
            color: Colors.white70,
          ),
          onChanged: (val) {
            searchedMovie = val;
          },
          decoration: InputDecoration(
            suffixIcon: GestureDetector(
              onTap: () {
                if (searchedMovie != '') {
                  StoreProvider.of<AppState>(context)
                      .dispatch(GetMoviesByName(searchedMovie));
                }
              },
              child: Icon(
                Icons.search,
                color: Colors.white,
              ),
            ),
            hintText: 'Search..',
            hintStyle: TextStyle(
              color: Colors.white54,
            ),
            border: InputBorder.none,
          ),
        ),
        backgroundColor: Color.fromRGBO(29, 52, 97, 1),
      ),
      body: SingleChildScrollView(
        controller: controller,
        child: MoviesContainer(
          builder: (BuildContext context, BuiltList<Movie> movies) {
            return (movies.length > 0 ||
                    StoreProvider.of<AppState>(context)
                            .state
                            .moviesState
                            .page !=
                        0)
                ? Column(
                    children: <Widget>[
                      SizedBox(height: 8),
                      GenreContainer(
                        builder: (BuildContext context, String genre) {
                          return DropdownButton<String>(
                            onChanged: (String value) {
                              StoreProvider.of<AppState>(context)
                                ..dispatch(UpdateGenre(value))
                                ..dispatch(const GetMovies());
                            },
                            value: genre,
                            items: genres.map(
                              (String genre) {
                                return DropdownMenuItem<String>(
                                  value: genre,
                                  child: Text(genre),
                                );
                              },
                            ).toList(),
                          );
                        },
                      ),
                      SizedBox(height: 16),
                      GridView.builder(
                        itemCount: movies.length,
                        physics: const NeverScrollableScrollPhysics(),
                        shrinkWrap: true,
                        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                          childAspectRatio: 0.75,
                          crossAxisCount: 3,
                          crossAxisSpacing: 4.0,
                          mainAxisSpacing: 16.0,
                        ),
                        itemBuilder: (BuildContext context, int index) {
                          return Container(
                            child: GestureDetector(
                              onTap: () async {
                                await StoreProvider.of<AppState>(context)
                                    .dispatch(ViewMovie(
                                        (movies[index].id).toString()));
                                Navigator.pushNamed(context, AppRoutes.movie);
                              },
                              child: Image(
                                image: NetworkImage(movies[index].poster_path),
                              ),
                            ),
                          );
                        },
                      ),
                      pageState(
                          StoreProvider.of<AppState>(context)
                              .state
                              .moviesState
                              .page,
                          context),
                    ],
                  )
                : Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: <Widget>[
                      SizedBox(
                        height: 60,
                      ),
                      Image(
                        image: AssetImage('assets/not_found.png'),
                      ),
                      SizedBox(height: 8),
                      Text(
                        'No movies found',
                        style: TextStyle(
                          color: Color.fromRGBO(29, 52, 97, 1),
                          fontWeight: FontWeight.bold,
                          fontSize: 20,
                        ),
                      )
                    ],
                  );
          },
        ),
      ),
    );
  }

  Widget pageState(int page, BuildContext context) {
    if (page == 0) {
      return Text('');
    } else {
      return GestureDetector(
        onTap: () {
          controller.jumpTo(controller.position.minScrollExtent);
          StoreProvider.of<AppState>(context).dispatch(const GetMovies());
        },
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.end,
            children: [
              Text(
                'Next',
                style: TextStyle(
                  fontSize: 19,
                ),
              ),
              SizedBox(width: 6),
              Image(
                width: 19,
                height: 19,
                image: AssetImage('assets/next.png'),
              ),
            ],
          ),
        ),
      );
    }
  }
}
