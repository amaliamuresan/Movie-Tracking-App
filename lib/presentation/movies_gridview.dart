import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:movie_app/actions/view_movie.dart';
import 'package:movie_app/models/movie.dart';
import 'package:movie_app/models/states/app_state.dart';
import 'package:movie_app/routes/routes.dart';

class MoviesGridView extends StatelessWidget {
  MoviesGridView({Key key, @required this.movies}) : super(key: key);
  final List<Movie> movies;

  @override
  Widget build(BuildContext context) {
    return GridView.builder(
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
    );
  }
}