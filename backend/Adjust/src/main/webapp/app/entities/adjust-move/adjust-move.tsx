import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './adjust-move.reducer';
import { IAdjustMove } from 'app/shared/model/adjust-move.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustMoveProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AdjustMove = (props: IAdjustMoveProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { adjustMoveList, match, loading } = props;
  return (
    <div>
      <h2 id="adjust-move-heading">
        <Translate contentKey="adjustApp.adjustMove.home.title">Adjust Moves</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.adjustMove.home.createLabel">Create new Adjust Move</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {adjustMoveList && adjustMoveList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustMove.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustMove.muscleName">Muscle Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustMove.muscleType">Muscle Type</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustMove.equipment">Equipment</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustMove.picture">Picture</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {adjustMoveList.map((adjustMove, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${adjustMove.id}`} color="link" size="sm">
                      {adjustMove.id}
                    </Button>
                  </td>
                  <td>{adjustMove.name}</td>
                  <td>{adjustMove.muscleName}</td>
                  <td>
                    <Translate contentKey={`adjustApp.MuscleType.${adjustMove.muscleType}`} />
                  </td>
                  <td>{adjustMove.equipment}</td>
                  <td>
                    {adjustMove.picture ? (
                      <div>
                        {adjustMove.pictureContentType ? (
                          <a onClick={openFile(adjustMove.pictureContentType, adjustMove.picture)}>
                            <img src={`data:${adjustMove.pictureContentType};base64,${adjustMove.picture}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {adjustMove.pictureContentType}, {byteSize(adjustMove.picture)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${adjustMove.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustMove.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustMove.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="adjustApp.adjustMove.home.notFound">No Adjust Moves found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ adjustMove }: IRootState) => ({
  adjustMoveList: adjustMove.entities,
  loading: adjustMove.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustMove);
