# takes NMPI_ROOT and NLC_ROOT from environment
SET(CMAKE_SYSTEM_NAME Aurora-VE)

SET(CMAKE_Fortran_COMPILER /opt/nec/ve/bin/nfort CACHE FILEPATH "Aurora Fortran compiler")
SET(CMAKE_CXX_COMPILER /opt/nec/ve/bin/nc++ CACHE FILEPATH "Aurora C++ compiler")
SET(CMAKE_C_COMPILER /opt/nec/ve/bin/ncc CACHE FILEPATH "Aurora C compiler")
SET(CMAKE_LINKER /opt/nec/ve/bin/nld CACHE FILEPATH "Aurora linker")
SET(CMAKE_AR /opt/nec/ve/bin/nar CACHE FILEPATH "Aurora archiver")
SET(CMAKE_RANLIB /opt/nec/ve/bin/nranlib CACHE FILEPATH "Aurora ranlib")

SET(OpenMP_Fortran_FLAGS "-fopenmp" CACHE STRING "Flag to enable OpenMP")
SET(OpenMP_CXX_FLAGS "-fopenmp" CACHE STRING "Flag to enable OpenMP")
SET(OpenMP_C_FLAGS "-fopenmp" CACHE STRING "Flag to enable OpenMP")

SET(MPI_C_COMPILER $ENV{NMPI_ROOT}/bin/mpicc CACHE FILEPATH "")
SET(MPI_C_INCLUDE_PATH $ENV{NMPI_ROOT}/include CACHE FILEPATH "")
SET(MPI_C_LIBRARIES $ENV{NMPI_ROOT}/lib64/ve/libmpi.a CACHE FILEPATH "")
SET(MPI_C_COMPILE_FLAGS "-D_MPIPP_INCLUDE" CACHE STRING "")

SET(MPI_CXX_COMPILER $ENV{NMPI_ROOT}/bin/mpic++ CACHE FILEPATH "")
SET(MPI_CXX_INCLUDE_PATH $ENV{NMPI_ROOT}/include CACHE FILEPATH "")
SET(MPI_CXX_LIBRARIES $ENV{NMPI_ROOT}/lib64/ve/libmpi++.a CACHE FILEPATH "")

SET(MPI_Fortran_COMPILER $ENV{NMPI_ROOT}/bin/mpif90 CACHE FILEPATH "")
SET(MPI_Fortran_INCLUDE_PATH $ENV{NMPI_ROOT}/lib/ve/module CACHE FILEPATH "")
SET(MPI_Fortran_LIBRARIES $ENV{NMPI_ROOT}/lib64/ve/libmpi.a CACHE FILEPATH "")
SET(MPI_Fortran_COMPILE_FLAGS "-D_MPIPP_INCLUDE" CACHE STRING "")

SET(CMAKE_C_FLAGS   "-std=gnu++14" CACHE STRING "" FORCE)
SET(CMAKE_CXX_FLAGS "-std=gnu++14" CACHE STRING "" FORCE)

SET(CMAKE_CROSSCOMPILING_EMULATOR "/opt/nec/ve/bin/ve_exec" CACHE FILEPATH "Command to execute VE binaries")

SET(BLAS_LIBRARIES $ENV{NLC_ROOT}/lib/libblas_sequential.a CACHE FILEPATH "BLAS library")
SET(LAPACK_LIBRARIES $ENV{NLC_ROOT}/lib/liblapack.a CACHE FILEPATH "LAPACK library")

set(CMAKE_C_STANDARD_COMPUTED_DEFAULT 11)
set(CMAKE_CXX_STANDARD_COMPUTED_DEFAULT 14)
set(CMAKE_Fortran_STANDARD_COMPUTED_DEFAULT 08)

set(CMAKE_CXX_USE_RESPONSE_FILE_FOR_OBJECTS OFF)