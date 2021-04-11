// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies

part of 'login.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

/// @nodoc
class _$LoginTearOff {
  const _$LoginTearOff();

// ignore: unused_element
  Login$ call(
      {@required String email,
      @required String password,
      @required void Function(AppAction) response}) {
    return Login$(
      email: email,
      password: password,
      response: response,
    );
  }

// ignore: unused_element
  LoginSuccessful successful(AppUser appUser) {
    return LoginSuccessful(
      appUser,
    );
  }

// ignore: unused_element
  LoginError error(Object error) {
    return LoginError(
      error,
    );
  }
}

/// @nodoc
// ignore: unused_element
const $Login = _$LoginTearOff();

/// @nodoc
mixin _$Login {
  @optionalTypeArgs
  TResult when<TResult extends Object>(
    TResult $default(
        String email, String password, void Function(AppAction) response), {
    @required TResult successful(AppUser appUser),
    @required TResult error(Object error),
  });
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object>(
    TResult $default(
        String email, String password, void Function(AppAction) response), {
    TResult successful(AppUser appUser),
    TResult error(Object error),
    @required TResult orElse(),
  });
  @optionalTypeArgs
  TResult map<TResult extends Object>(
    TResult $default(Login$ value), {
    @required TResult successful(LoginSuccessful value),
    @required TResult error(LoginError value),
  });
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object>(
    TResult $default(Login$ value), {
    TResult successful(LoginSuccessful value),
    TResult error(LoginError value),
    @required TResult orElse(),
  });
}

/// @nodoc
abstract class $LoginCopyWith<$Res> {
  factory $LoginCopyWith(Login value, $Res Function(Login) then) =
      _$LoginCopyWithImpl<$Res>;
}

/// @nodoc
class _$LoginCopyWithImpl<$Res> implements $LoginCopyWith<$Res> {
  _$LoginCopyWithImpl(this._value, this._then);

  final Login _value;
  // ignore: unused_field
  final $Res Function(Login) _then;
}

/// @nodoc
abstract class $Login$CopyWith<$Res> {
  factory $Login$CopyWith(Login$ value, $Res Function(Login$) then) =
      _$Login$CopyWithImpl<$Res>;
  $Res call({String email, String password, void Function(AppAction) response});
}

/// @nodoc
class _$Login$CopyWithImpl<$Res> extends _$LoginCopyWithImpl<$Res>
    implements $Login$CopyWith<$Res> {
  _$Login$CopyWithImpl(Login$ _value, $Res Function(Login$) _then)
      : super(_value, (v) => _then(v as Login$));

  @override
  Login$ get _value => super._value as Login$;

  @override
  $Res call({
    Object email = freezed,
    Object password = freezed,
    Object response = freezed,
  }) {
    return _then(Login$(
      email: email == freezed ? _value.email : email as String,
      password: password == freezed ? _value.password : password as String,
      response: response == freezed
          ? _value.response
          : response as void Function(AppAction),
    ));
  }
}

/// @nodoc
class _$Login$ implements Login$ {
  const _$Login$(
      {@required this.email, @required this.password, @required this.response})
      : assert(email != null),
        assert(password != null),
        assert(response != null);

  @override
  final String email;
  @override
  final String password;
  @override
  final void Function(AppAction) response;

  @override
  String toString() {
    return 'Login(email: $email, password: $password, response: $response)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other is Login$ &&
            (identical(other.email, email) ||
                const DeepCollectionEquality().equals(other.email, email)) &&
            (identical(other.password, password) ||
                const DeepCollectionEquality()
                    .equals(other.password, password)) &&
            (identical(other.response, response) ||
                const DeepCollectionEquality()
                    .equals(other.response, response)));
  }

  @override
  int get hashCode =>
      runtimeType.hashCode ^
      const DeepCollectionEquality().hash(email) ^
      const DeepCollectionEquality().hash(password) ^
      const DeepCollectionEquality().hash(response);

  @JsonKey(ignore: true)
  @override
  $Login$CopyWith<Login$> get copyWith =>
      _$Login$CopyWithImpl<Login$>(this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object>(
    TResult $default(
        String email, String password, void Function(AppAction) response), {
    @required TResult successful(AppUser appUser),
    @required TResult error(Object error),
  }) {
    assert($default != null);
    assert(successful != null);
    assert(error != null);
    return $default(email, password, response);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object>(
    TResult $default(
        String email, String password, void Function(AppAction) response), {
    TResult successful(AppUser appUser),
    TResult error(Object error),
    @required TResult orElse(),
  }) {
    assert(orElse != null);
    if ($default != null) {
      return $default(email, password, response);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object>(
    TResult $default(Login$ value), {
    @required TResult successful(LoginSuccessful value),
    @required TResult error(LoginError value),
  }) {
    assert($default != null);
    assert(successful != null);
    assert(error != null);
    return $default(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object>(
    TResult $default(Login$ value), {
    TResult successful(LoginSuccessful value),
    TResult error(LoginError value),
    @required TResult orElse(),
  }) {
    assert(orElse != null);
    if ($default != null) {
      return $default(this);
    }
    return orElse();
  }
}

abstract class Login$ implements Login {
  const factory Login$(
      {@required String email,
      @required String password,
      @required void Function(AppAction) response}) = _$Login$;

  String get email;
  String get password;
  void Function(AppAction) get response;
  @JsonKey(ignore: true)
  $Login$CopyWith<Login$> get copyWith;
}

/// @nodoc
abstract class $LoginSuccessfulCopyWith<$Res> {
  factory $LoginSuccessfulCopyWith(
          LoginSuccessful value, $Res Function(LoginSuccessful) then) =
      _$LoginSuccessfulCopyWithImpl<$Res>;
  $Res call({AppUser appUser});
}

/// @nodoc
class _$LoginSuccessfulCopyWithImpl<$Res> extends _$LoginCopyWithImpl<$Res>
    implements $LoginSuccessfulCopyWith<$Res> {
  _$LoginSuccessfulCopyWithImpl(
      LoginSuccessful _value, $Res Function(LoginSuccessful) _then)
      : super(_value, (v) => _then(v as LoginSuccessful));

  @override
  LoginSuccessful get _value => super._value as LoginSuccessful;

  @override
  $Res call({
    Object appUser = freezed,
  }) {
    return _then(LoginSuccessful(
      appUser == freezed ? _value.appUser : appUser as AppUser,
    ));
  }
}

/// @nodoc
class _$LoginSuccessful implements LoginSuccessful {
  const _$LoginSuccessful(this.appUser) : assert(appUser != null);

  @override
  final AppUser appUser;

  @override
  String toString() {
    return 'Login.successful(appUser: $appUser)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other is LoginSuccessful &&
            (identical(other.appUser, appUser) ||
                const DeepCollectionEquality().equals(other.appUser, appUser)));
  }

  @override
  int get hashCode =>
      runtimeType.hashCode ^ const DeepCollectionEquality().hash(appUser);

  @JsonKey(ignore: true)
  @override
  $LoginSuccessfulCopyWith<LoginSuccessful> get copyWith =>
      _$LoginSuccessfulCopyWithImpl<LoginSuccessful>(this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object>(
    TResult $default(
        String email, String password, void Function(AppAction) response), {
    @required TResult successful(AppUser appUser),
    @required TResult error(Object error),
  }) {
    assert($default != null);
    assert(successful != null);
    assert(error != null);
    return successful(appUser);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object>(
    TResult $default(
        String email, String password, void Function(AppAction) response), {
    TResult successful(AppUser appUser),
    TResult error(Object error),
    @required TResult orElse(),
  }) {
    assert(orElse != null);
    if (successful != null) {
      return successful(appUser);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object>(
    TResult $default(Login$ value), {
    @required TResult successful(LoginSuccessful value),
    @required TResult error(LoginError value),
  }) {
    assert($default != null);
    assert(successful != null);
    assert(error != null);
    return successful(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object>(
    TResult $default(Login$ value), {
    TResult successful(LoginSuccessful value),
    TResult error(LoginError value),
    @required TResult orElse(),
  }) {
    assert(orElse != null);
    if (successful != null) {
      return successful(this);
    }
    return orElse();
  }
}

abstract class LoginSuccessful implements Login {
  const factory LoginSuccessful(AppUser appUser) = _$LoginSuccessful;

  AppUser get appUser;
  @JsonKey(ignore: true)
  $LoginSuccessfulCopyWith<LoginSuccessful> get copyWith;
}

/// @nodoc
abstract class $LoginErrorCopyWith<$Res> {
  factory $LoginErrorCopyWith(
          LoginError value, $Res Function(LoginError) then) =
      _$LoginErrorCopyWithImpl<$Res>;
  $Res call({Object error});
}

/// @nodoc
class _$LoginErrorCopyWithImpl<$Res> extends _$LoginCopyWithImpl<$Res>
    implements $LoginErrorCopyWith<$Res> {
  _$LoginErrorCopyWithImpl(LoginError _value, $Res Function(LoginError) _then)
      : super(_value, (v) => _then(v as LoginError));

  @override
  LoginError get _value => super._value as LoginError;

  @override
  $Res call({
    Object error = freezed,
  }) {
    return _then(LoginError(
      error == freezed ? _value.error : error,
    ));
  }
}

@Implements(ErrorAction)

/// @nodoc
class _$LoginError implements LoginError {
  const _$LoginError(this.error) : assert(error != null);

  @override
  final Object error;

  @override
  String toString() {
    return 'Login.error(error: $error)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other is LoginError &&
            (identical(other.error, error) ||
                const DeepCollectionEquality().equals(other.error, error)));
  }

  @override
  int get hashCode =>
      runtimeType.hashCode ^ const DeepCollectionEquality().hash(error);

  @JsonKey(ignore: true)
  @override
  $LoginErrorCopyWith<LoginError> get copyWith =>
      _$LoginErrorCopyWithImpl<LoginError>(this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object>(
    TResult $default(
        String email, String password, void Function(AppAction) response), {
    @required TResult successful(AppUser appUser),
    @required TResult error(Object error),
  }) {
    assert($default != null);
    assert(successful != null);
    assert(error != null);
    return error(this.error);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object>(
    TResult $default(
        String email, String password, void Function(AppAction) response), {
    TResult successful(AppUser appUser),
    TResult error(Object error),
    @required TResult orElse(),
  }) {
    assert(orElse != null);
    if (error != null) {
      return error(this.error);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object>(
    TResult $default(Login$ value), {
    @required TResult successful(LoginSuccessful value),
    @required TResult error(LoginError value),
  }) {
    assert($default != null);
    assert(successful != null);
    assert(error != null);
    return error(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object>(
    TResult $default(Login$ value), {
    TResult successful(LoginSuccessful value),
    TResult error(LoginError value),
    @required TResult orElse(),
  }) {
    assert(orElse != null);
    if (error != null) {
      return error(this);
    }
    return orElse();
  }
}

abstract class LoginError implements Login, ErrorAction {
  const factory LoginError(Object error) = _$LoginError;

  Object get error;
  @JsonKey(ignore: true)
  $LoginErrorCopyWith<LoginError> get copyWith;
}
